package com.gaji.SingleBungle.cafe.controller;

import java.util.HashMap;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaji.SingleBungle.cafe.model.service.CafeService;
import com.gaji.SingleBungle.cafe.model.vo.Cafe;
import com.gaji.SingleBungle.cafe.model.vo.CafeAttachment;
import com.gaji.SingleBungle.cafe.model.vo.CafeLike;
import com.gaji.SingleBungle.cafe.model.vo.CafePageInfo;
import com.gaji.SingleBungle.cafe.model.vo.CafeReport;
import com.gaji.SingleBungle.cafe.model.vo.CafeSearch;
import com.gaji.SingleBungle.member.model.vo.Member;
import com.google.gson.Gson;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/cafe/*")
public class CafeController {
	
	@Autowired
	private CafeService service;
	
	private String swalIcon = null;
	private String swalTitle = null;
	private String swalText = null;

	
	
	// 게시글 목록 조회 Controller
	@RequestMapping("list")
	public String CafeList(@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model,
			@ModelAttribute("loginMember") Member loginMember, RedirectAttributes ra) {

		String url = null;

		if (loginMember != null) {
			if (loginMember.getMemberGrade().charAt(0) == 'T') {
				swalIcon = "error";
				swalTitle = "맛집 게시판은 2등급부터 이용할 수 있습니다.";
				url = "redirect:/";
			} else {

				CafePageInfo cpInfo = service.getPageInfo(cp);

				List<Cafe> cList = service.selectList(cpInfo);

				List<CafeLike> likeInfo = service.selectLike(loginMember.getMemberNo());

				// 썸네일
				if (cList != null && !cList.isEmpty()) {
					List<CafeAttachment> thumbnailList = service.selectThumbnailList(cList);

					if (thumbnailList != null) {
						model.addAttribute("thList", thumbnailList);
					}

				}

				model.addAttribute("cList", cList);
				model.addAttribute("cpInfo", cpInfo);
				model.addAttribute("likeInfo", likeInfo);

				url = "cafe/cafeList";

			}
			
		} else {

			swalIcon = "error";
			swalTitle = "로그인 후 이용해주세요.";
			url = "redirect:/";

		}
		ra.addFlashAttribute("swalIcon", swalIcon);
		ra.addFlashAttribute("swalTitle", swalTitle);
		return url;
	}
	
	
	
	// 검색 Controller
	@RequestMapping("search")
	public String boardSearch(@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
								@RequestParam(value="sk",required = false) String sk, 
								@RequestParam(value="sv",required = false) String sv,
								@RequestParam(value="ct",required = false) String ct,
								@RequestParam(value="sort",required = false) String sort, 
								@ModelAttribute("cSearch") CafeSearch cSearch,
								Model model) {
		
		cSearch.setSk(sk);
		cSearch.setSv(sv);
		cSearch.setCt(ct);
		cSearch.setSort(sort);
		
		CafePageInfo cpInfo = service.getSearchPageInfo(cSearch, cp);
		
		List<Cafe> cList = service.selectSearchList(cSearch, cpInfo);
		
		if(!cList.isEmpty()) {
			List<CafeAttachment> thList = service.selectThumbnailList(cList);
			model.addAttribute("thList", thList);
		}
		
		model.addAttribute("cList", cList);
		model.addAttribute("cpInfo", cpInfo);
		model.addAttribute("cSearch", cSearch);
		
		return "cafe/cafeList";
	}
	
	
	// 게시글 상세조회 Controller
	@RequestMapping("{cafeNo}")
	public String cafeView(@PathVariable("cafeNo") int cafeNo, Model model, @ModelAttribute("loginMember") Member loginMember,
			@RequestHeader(value = "referer", required = false) String referer, RedirectAttributes ra) {
		
		Cafe cafe = service.selectCafe(cafeNo);
		
		String url = null;
		
		if (cafe != null) {
			
			// 해당 게시글에 좋아요를 눌렀는지 확인
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("memberNo", loginMember.getMemberNo());
			map.put("cafeNo", cafeNo);
			
			int like = service.selectLikePushed(map);
			model.addAttribute("like", like);
			
			
			List<Cafe> cafeList = service.cafeListTop3();
			
			List<CafeAttachment> attachmentList = service.selectAttachmentList(cafeNo);
			
			if (attachmentList != null && !attachmentList.isEmpty()) {
				model.addAttribute("attachmentList", attachmentList);
			}
			
			// 썸네일
			if (cafeList != null && !cafeList.isEmpty()) { // 게시글 목록 조회 성공 시
				List<CafeAttachment> thumbnailList = service.selectThumbnailList(cafeList);

				if (thumbnailList != null) {
					model.addAttribute("thList", thumbnailList);
				}

			}
			
			
			model.addAttribute("cafe", cafe);
			model.addAttribute("cafeList", cafeList);
			url = "cafe/cafeView";
		} else {
			
			if (referer == null) {
				url = "redirect:../list";
			} else {
				url = "redirect:" + referer;
			}

			ra.addFlashAttribute("swalIcon", "error");
			ra.addFlashAttribute("swalTitle", "존재하지 않는 게시글입니다.");
		}
		
		return url;
	}
	
	// 게시글 등록 화면 전환용 Controller
	@RequestMapping("insert")
	public String cafeInsert() {
		return "cafe/cafeInsert";
	}
	
	// 게시글 등록 Controller
	@RequestMapping("insertAction")
	public String insertAction(@ModelAttribute Cafe cafe, @ModelAttribute("loginMember") Member loginMember,
			@RequestParam(value = "images", required = false) List<MultipartFile> images, HttpServletRequest request,
			RedirectAttributes ra) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", loginMember.getMemberNo());
		map.put("cafeTitle", cafe.getCafeTitle());
		map.put("cafeContent", cafe.getCafeContent());
		map.put("categoryCode", cafe.getCategoryCode());
		map.put("cafeName", cafe.getCafeName());
		map.put("cafeAddress", cafe.getCafeAddress());
		
		String savePath = null;
		
		savePath = request.getSession().getServletContext().getRealPath("resources/cafeImages");
		
		int result = service.insertCafe(map, images, savePath);
		
		String url = null;
		
		if (result > 0) {
			swalIcon = "success";
			swalTitle = "게시글 등록 성공";
			url = "redirect:" + result;
			
			// 목록 버튼 경로
			request.getSession().setAttribute("returnListURL", "list");
		} else {
			swalIcon = "error";
			swalTitle = "게시글 삽입 실패";
			url = "redirect:insert";
		}
		
		ra.addFlashAttribute("swalIcon", swalIcon);
		ra.addFlashAttribute("swalTitle", swalTitle);
		
		return url;
	}
	
	// summernote에 업로드된 이미지 저장 Controller
	
	@ResponseBody
	@RequestMapping(value = { "insertImage", "{cafeNo}/insertImage" })
	public String insertImage(HttpServletRequest request,
			 				@RequestParam("uploadFile") MultipartFile uploadFile) {
		
		String savePath = request.getSession().getServletContext().getRealPath("resources/cafeImages");
		
		CafeAttachment at = service.insertImage(uploadFile, savePath);
		
		return new Gson().toJson(at);
	}
	
	
	
	// 게시글 수정 화면 전환용 Controller
	@RequestMapping("{cafeNo}/update")
	public String cafeUpdate(@PathVariable("cafeNo") int cafeNo, Model model) {
		
		Cafe cafe = service.selectCafe(cafeNo);
		
		if(cafe != null) {
			List<CafeAttachment> attachmentList = service.selectAttachmentList(cafeNo);
			model.addAttribute("attachmentList", attachmentList);
		}
		
		model.addAttribute("cafe", cafe);
		
		return "cafe/cafeUpdate";
	}
	
	// 게시글 수정 Controller
	@RequestMapping("{cafeNo}/updateAction")
	public String updateAction(@PathVariable("cafeNo") int cafeNo,
							@ModelAttribute Cafe updateCafe,
							Model model, RedirectAttributes ra, HttpServletRequest request) {
		
		updateCafe.setCafeNo(cafeNo);
		
		int result = service.updateCafe(updateCafe);
		
		String url = null;

		if (result > 0) {
			swalIcon = "success";
			swalTitle = "게시글 수정 성공";
			url = "redirect:../" + cafeNo;
		} else {
			swalIcon = "error";
			swalTitle = "게시글 수정 실패";
			url = "redirect:" + request.getHeader("referer");
		}

		ra.addFlashAttribute("swalIcon", swalIcon);
		ra.addFlashAttribute("swalTitle", swalTitle);

		return url;
	}
	
	// 게시글 삭제 Controller
	@RequestMapping("{cafeNo}/delete")
	public String deleteCafe(@PathVariable("cafeNo") int cafeNo,
							@ModelAttribute Cafe cafe,
							HttpServletRequest request, RedirectAttributes ra) {
		
		cafe.setCafeNo(cafeNo);
		
		int result = service.deleteCafe(cafe);
		
		String url = null;
		
		if (result > 0) {
			swalIcon = "success";
			swalTitle = "게시글 삭제 성공";
			url = "redirect:../list";
		} else {
			swalIcon = "error";
			swalTitle = "게시글 삭제 실패";
			url = "redirect:" + request.getHeader("referer");
		}
		
		ra.addFlashAttribute("swalIcon", swalIcon);
		ra.addFlashAttribute("swalTitle", swalTitle);
		
		return url;
	}
	
	// 좋아요 증가 Controller
	@ResponseBody
	@RequestMapping("increaseLike")
	public int increaseLike(@RequestParam int cafeNo,
							@ModelAttribute("loginMember") Member loginMember) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("memberNo", loginMember.getMemberNo());
		map.put("cafeNo", cafeNo);
		
		int result = service.increaseLike(map);
		
		return result;
	}
	
	// 좋아요 감소 Controller
	@ResponseBody
	@RequestMapping("decreaseLike")
	public int decreaseLike(@RequestParam int cafeNo,
						@ModelAttribute("loginMember") Member loginMember) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("memberNo", loginMember.getMemberNo());
		map.put("cafeNo", cafeNo);
		
		int result = service.decreaseLike(map);
		
		return result;
		
	}
	
	
	// 신고 페이지 연결 Controller
	@RequestMapping("cafeReport/{cafeNo}")
	public String cafeReport(@PathVariable int cafeNo, Model model ) {
		model.addAttribute("cafeNo", cafeNo);
		return "cafe/cafeReport";
	}
	
	// 게시글 신고 등록 Controller
	@RequestMapping("cafeReportAction")
	public String insertCafeReport(@ModelAttribute CafeReport report, @RequestParam("cafeNo") int cafeNo,
					@ModelAttribute("loginMember") Member loginMember,
					HttpServletRequest request, RedirectAttributes ra) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", loginMember.getMemberNo());
		map.put("cafeNo", cafeNo);
		
		
		map.put("reportTitle", report.getReportTitle());
		map.put("reportContent", report.getReportContent());
		map.put("categoryCode", report.getCategoryCode());
		
		int result = service.insertCafeReport(map);
		
		String url = "redirect:" + request.getHeader("referer");
		
		if (result > 0) {
			swalIcon = "success";
			swalTitle = "신고가 접수되었습니다.";
			//url = "redirect:" + result;
		} else {
			swalIcon = "error";
			swalTitle = "신고 접수 실패";
			//url = "redirect:" + request.getHeader("referer");
		}
		
		ra.addFlashAttribute("swalIcon", swalIcon);
		ra.addFlashAttribute("swalTitle", swalTitle);
		
		return url;
	}
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	// 관리자(admin) 삭제된 게시글 상세조회 Controller
	@RequestMapping("deleteManage/{boardCode}/{boardNo}")
	public String deleteManageBoard(@PathVariable("boardCode") int boardCode, @PathVariable("boardNo") int cafeNo, Model model,
			@RequestHeader(value="referer",required=false) String referer, RedirectAttributes ra, @ModelAttribute("loginMember") Member loginMember) {
	
		Cafe cafe = service.selectDeleteCafe(cafeNo);
		
		String url = null;
		
		if (cafe != null) {
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("memberNo", loginMember.getMemberNo());
			map.put("cafeNo", cafeNo);
			
			int like = service.selectLikePushed(map);
			model.addAttribute("like", like);
			
			
			List<Cafe> cafeList = service.cafeListTop3();
			
			List<CafeAttachment> attachmentList = service.selectAttachmentList(cafeNo);
			
			if (attachmentList != null && !attachmentList.isEmpty()) {
				model.addAttribute("attachmentList", attachmentList);
			}
			
			// 썸네일
			if (cafeList != null && !cafeList.isEmpty()) {
				List<CafeAttachment> thumbnailList = service.selectThumbnailList(cafeList);

				if (thumbnailList != null) {
					model.addAttribute("thList", thumbnailList);
				}

			}
			
			model.addAttribute("cafe", cafe);
			model.addAttribute("cafeList", cafeList);
			url = "cafe/cafeView";
		} else {
			
			if (referer == null) {
				url = "${contextPath}/admin/boardManage";
			} else {
				url = "redirect:" + referer;
			}

			ra.addFlashAttribute("swalIcon", "error");
			ra.addFlashAttribute("swalTitle", "존재하지 않는 게시글입니다.");
		}
		
		return url;
	}

}
