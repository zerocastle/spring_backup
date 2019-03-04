package kr.pe.patissier.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//	@RequestMapping(value = "register", method = RequestMethod.GET)
//	public void registerGET(BoardVO board, Model model, HttpServletRequest rqu, HttpServletResponse res)
//			throws Exception {
//		logger.info("register get ...");
//
//	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGET(BoardVO board, Model model, HttpServletRequest rqu, HttpServletResponse res)
			throws Exception {
		logger.info("register get ...");

		return "/board/register";

	}

//	@RequestMapping(value = "register", method = RequestMethod.POST)
//	public void registerPOST(BoardVO board, Model model, HttpServletRequest rqu, HttpServletResponse res)
//			throws Exception {
//		logger.info("register post ...");
//		logger.info(board.toString());
//
//		boardService.regist(board);
//		model.addAttribute("result", "success");
//		res.sendRedirect("/board/listAll"); // 아래 메서드를 호출 하게 만든다.
////		return "board/success";
//
//	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model, RedirectAttributes re) throws Exception {
		logger.info("register post ...");
		logger.info(board.toString());

		boardService.regist(board);
//		model.addAttribute("result", "success");

		re.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";

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

	@RequestMapping(value = "remove2", method = RequestMethod.POST)
	public String remove2(@RequestParam Map map, RedirectAttributes rttr) throws Exception {

		boardService.remove2(map);
		rttr.addFlashAttribute("msg", "SUCCESS");
		System.out.println("remove2 메서드 실행 했당 ~~!!!!");
		return "redirect:/board/listAll";
	}

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
	// test 
	@RequestMapping(value = "listPage")
	public String listpage(Criteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", boardService.listCriteria(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(13);
		model.addAttribute("pageMaker", pageMaker);
		return "board/listPage";
	}

}
