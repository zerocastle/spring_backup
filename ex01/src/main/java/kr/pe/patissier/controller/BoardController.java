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

		re.addFlashAttribute("msg", "SUCCESS");// model 과다르게 url상에 보이지 않는 숨겨진 데이터의 형태로 전달한다.
		return "redirect:/board/listAll";

	}

	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list ....");
		model.addAttribute("list", boardService.listAll());

	}

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info(bno + "글을 읽어 들입니다.");
		model.addAttribute(boardService.read(bno)); // 넘오온 클래스가 BoardVO 이기 때문에 자동으로 boardVO로 이름이 지어진다.

	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(@RequestParam Map map, RedirectAttributes re) throws Exception {
		boardService.remove(map);
		re.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void modify(@RequestParam Map map, Model model) throws Exception {
		model.addAttribute(boardService.read((int)map.get("bno")));
	}

}
