package kr.pe.patissier.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.pe.patissier.domain.BoardVO;
import kr.pe.patissier.domain.Criteria;
import kr.pe.patissier.domain.PageMaker;
import kr.pe.patissier.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGET(BoardVO board, Model model, HttpServletRequest rqu, HttpServletResponse res)
			throws Exception {
		logger.info("register get ...");

		return "/board/register";

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model, RedirectAttributes re) throws Exception {
		logger.info("register post ...");
		logger.info(board.toString());

		boardService.regist(board);
//		model.addAttribute("result", "success");

		re.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listPage";

	}

	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list ....");
		model.addAttribute("list", boardService.listAll());

	}

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("show all list ....");
		System.out.println("넘오온 bno 값 : " + bno);
		model.addAttribute(boardService.read(bno));

	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {

		boardService.remove(bno);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";
	}

//	@RequestMapping(value = "remove2", method = RequestMethod.POST)
//	public String remove2(@RequestParam Map map, RedirectAttributes rttr) throws Exception {
//
//		boardService.remove2(map);
//		rttr.addFlashAttribute("msg", "SUCCESS");
//		System.out.println("remove2 메서드 실행 했당 ~~!!!!");
//		return "redirect:/board/listAll";
//	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("mod post ...");
		boardService.modify(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "modify2", method = RequestMethod.GET)
	public String modifyGET2(@RequestParam Map map, Model model) throws Exception {
		model.addAttribute(boardService.read((int) map.get("bno")));
		return "board/modify";
	}

	@RequestMapping(value = "modify2", method = RequestMethod.POST)
	public String modifyPOST2(@RequestParam Map map, RedirectAttributes rttr) throws Exception {
		logger.info("mod post2 ...");
		boardService.modify2(map);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redircet:/board/listAll";
	}

	@RequestMapping(value = "listCri", method = RequestMethod.GET)
	public String listCri(Criteria cri, Model model) throws Exception {
		logger.info("show list page with Criteria ... ....");
		model.addAttribute("list", boardService.listCriteria(cri));

		return "board/listCri";

	}

	/* 페이징 처리한 부분 최종 컨트롤러 */

	// test 페이징 처리
	@RequestMapping(value = "listPage")
	public String listpage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("listPage 메소드 호출 시  : " + cri.toString());

		model.addAttribute("list", boardService.listCriteria(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
		return "board/listPage";
	}

	// pageMaker query 부분 query readpage
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model)
			throws Exception {
		logger.info("pageMaker 부분 : " + bno);
		model.addAttribute(boardService.read(bno));
		return "/board/readPage";
	}

	// remove
	@RequestMapping(value = "removePage", method = RequestMethod.POST)
	public String remove2(@RequestParam Map map, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr)
			throws Exception {
		// 폼에 있는 데이터들이 Map으로 넘오온다
		logger.info("remove page 부분 : " + cri.toString());
		boardService.remove2(map);
		rttr.addFlashAttribute("page", cri.getPage());
		rttr.addFlashAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listPage";
	}

	// modify
	@RequestMapping(value = "modifyPage", method = RequestMethod.GET)
	public String modifyPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model)
			throws Exception {
		System.out.println("넘오언 cri" + cri.toString());
		model.addAttribute("boardVO", boardService.read(bno));
		return "/board/modifyPage";
	}

	@RequestMapping(value = "modifyPage", method = RequestMethod.POST)
	public String modifyPagePost(@RequestParam Map map, @ModelAttribute("cri") Criteria cri, Model model,
			RedirectAttributes rttr) throws Exception {
		logger.info("모디파이 페이지================================================== : " + map.toString());
		boardService.modify2(map);
		rttr.addFlashAttribute("page", cri.getPage()); 
		rttr.addFlashAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listPage";
	}

}
