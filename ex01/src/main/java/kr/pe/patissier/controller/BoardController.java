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
//		res.sendRedirect("/board/listAll"); // �Ʒ� �޼��带 ȣ�� �ϰ� �����.
////		return "board/success";
//
//	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model, RedirectAttributes re) throws Exception {
		logger.info("register post ...");
		logger.info(board.toString());

		boardService.regist(board);
//		model.addAttribute("result", "success");

		re.addFlashAttribute("msg", "SUCCESS");// model ���ٸ��� url�� ������ �ʴ� ������ �������� ���·� �����Ѵ�.
		return "redirect:/board/listAll";

	}

	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list ....");
		model.addAttribute("list", boardService.listAll());

	}

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info(bno + "���� �о� ���Դϴ�.");
		model.addAttribute(boardService.read(bno)); // �ѿ��� Ŭ������ BoardVO �̱� ������ �ڵ����� boardVO�� �̸��� ��������.

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
