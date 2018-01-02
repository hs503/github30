package bmsProject.mvc.Spring.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import bmsProject.mvc.Spring.service.BoardServiceImpl;
import bmsProject.mvc.Spring.service.BookServiceImpl;
import bmsProject.mvc.Spring.service.HostServiceImpl;
import bmsProject.mvc.Spring.service.MemberServiceImpl;


@Controller
public class BMSController{
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	BookServiceImpl bookService;
	@Autowired
	MemberServiceImpl memberService;
	@Autowired
	HostServiceImpl hostService;

	//member--------------------------------------------------------------------------------
	@RequestMapping("main")
	public String bmsMain(HttpServletRequest req, Model model) {
		System.out.println("/bmsMain()");
		boardService.BoardList(req, model);
		bookService.bookList(req, model);
		return "bmsProject/bmsMain";
	}
	
	//�α��� ������ �̵�
	@RequestMapping("login")
	public String login(HttpServletRequest req, Model model) {
		System.out.println("/login()");		
		return "member/login";
	}
	
	//ȸ������ �Է������� �̵�
	@RequestMapping("inputForm")
	public String inputForm(HttpServletRequest req, Model model) {
		System.out.println("/inputForm()");		
		return "member/inputForm";
	}
	//ȸ������ ����|���� ������
	@RequestMapping("inputPro")
	public String inputPro(HttpServletRequest req, Model model) {
		System.out.println("/inputPro()");		
		memberService.inputPro(req, model);		
		return "member/inputPro";
	}
	//id�ߺ�Ȯ�� ó���ϴ� �˾� ������
	@RequestMapping("confirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		System.out.println("/confirmId()");
		
		memberService.confirmId(req, model);
		
		return "member/confirmId";
	}
	//Email Ȯ��/�ߺ�ó�� ������
	@RequestMapping("confirmEmail")
	public String confirmEmail(HttpServletRequest req, Model model) {
		System.out.println("/confirmEmail()");
		
		memberService.confirmEmail(req, model);
		
		return "member/confirmEmail";
	}
	// �α��� Ȯ�ι�ư ��������
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest req, Model model) {
		System.out.println("/loginPro()");
		
		memberService.loginPro(req, model);
		
		return "member/login";
	}
	// �α׾ƿ�
	@RequestMapping("logout")
	public String logout(HttpServletRequest req, Model model) {
		System.out.println("/logout()");
		req.getSession().setAttribute("memId", null);
		req.getSession().setAttribute("memName", null);
		req.setAttribute("cnt", 2);
		boardService.BoardList(req, model);
		bookService.bookList(req, model);
		
		return "bmsProject/bmsMain";
	}
	// ȸ������ �����ϱ� �� ��й�ȣ �Է��ϴ� ������
	@RequestMapping("modifyForm")
	public String modifyForm(HttpServletRequest req, Model model) {
		System.out.println("/modifyForm()");

		return "member/modifyForm";
	}
	// ȸ������ ���� ������
	@RequestMapping("modifyView")
	public String modifyView(HttpServletRequest req, Model model) {
		System.out.println("/modifyView()");
		
		memberService.modifyView(req, model);
		
		return "member/modifyView";
	}
	// ȸ������ ���� ó�� ������
	@RequestMapping("modifyPro")
	public String modifyPro(HttpServletRequest req, Model model) {
		System.out.println("/modifyPro()");
		
		memberService.modifyPro(req, model);
		
		return "member/modifyPro";
	}
	// ȸ��Ż��
	@RequestMapping("delete")
	public String delete(HttpServletRequest req, Model model) {
		System.out.println("/delete()");
		
		memberService.delete(req, model);
		
		req.getSession().setAttribute("memId", null);
		req.getSession().setAttribute("memName", null);
		
		return "member/delete";
	}
	//��ٱ��� ����
	@RequestMapping("cart")
	public String cart(HttpServletRequest req, Model model) {
		System.out.println("/cart()");
		
		memberService.cartList(req, model);
		
		return "member/cart";
	}
	//��ٱ��� �߰�
	@RequestMapping("cartInsert")
	public String cartInsert(HttpServletRequest req, Model model) {
		System.out.println("/cartInsert()");
		
		memberService.cartInsert(req, model);
		bookService.serchBook(req, model);
		
		return "bmsProject/detailPage";
	}
	//��ٱ��� ����
	@RequestMapping("cartDelete")
	public String cartDelete(HttpServletRequest req, Model model) {
		System.out.println("/cartDelete()");
		
		memberService.cartDelete(req, model);
		memberService.cartList(req, model);
		
		return "member/cart";
	}
	//å ����
	@RequestMapping("buyBookPro")
	public String buyBookPro(HttpServletRequest req, Model model) {
		System.out.println("/buyBookPro()");
		int num=Integer.parseInt(req.getParameter("num"));
		String title=req.getParameter("title");
		int amount=Integer.parseInt(req.getParameter("amount"));
		int price=Integer.parseInt(req.getParameter("price"));
		int code=Integer.parseInt(req.getParameter("code"));
		
		req.setAttribute("title", title);
		req.setAttribute("amount", amount);
		req.setAttribute("price", price);
		req.setAttribute("code", code);
		req.setAttribute("num", num);
		memberService.AmountTest(req, model);
		int AmountTest=(Integer) req.getAttribute("cnt");
	
		if(AmountTest>0) {
			req.setAttribute("AmountTest", AmountTest);
			memberService.cartList(req, model);			
			
			return "/member/cart";
		}else {
			return "/member/buyBookPro";
		}			
	
	}
	@RequestMapping("buyBook")
	public String buyBook(HttpServletRequest req, Model model) {
		System.out.println("/buyBook()");
		int num=Integer.parseInt(req.getParameter("num"));
		String title=req.getParameter("title");
		int amount=Integer.parseInt(req.getParameter("amount"));
		int price=Integer.parseInt(req.getParameter("price"));
		int code=Integer.parseInt(req.getParameter("code"));
		
		req.setAttribute("num", num);
		req.setAttribute("title", title);
		req.setAttribute("amount", amount);
		req.setAttribute("price", price);
		req.setAttribute("code", code);
		
		/*�Ѱܹ��� �� : num,title,price,amount,code*/
	
		memberService.buyBook(req, model);	
		memberService.cartList(req, model);
		
		return "/member/cart";
	}
	@RequestMapping("buyList")
	public String buyList(HttpServletRequest req, Model model) {
		System.out.println("/buyList()");

		memberService.buyList(req,model);

		return "member/buyList";
	}
	@RequestMapping("refund")
	public String refund(HttpServletRequest req, Model model) {
		System.out.println("/refund()");

		memberService.refund(req,model);
		memberService.buyList(req,model);
		
		return "member/buyList";
	}
	@RequestMapping("Confirmation")
	public String Confirmation(HttpServletRequest req, Model model) {
		System.out.println("/Confirmation()");
		
		memberService.Confirmation(req,model);
		memberService.buyList(req,model);
		
		return "member/buyList";
	}
	@RequestMapping("serch")
	public String serch(HttpServletRequest req, Model model) {
		System.out.println("/serch()");

		memberService.serch(req,model);

		return "bmsProject/serch";
	}
	@RequestMapping("contact")
	public String contact(HttpServletRequest req, Model model) {
		System.out.println("/contact()");

		return "bmsProject/contact";
	}
	@RequestMapping("amountChange")
	public String amountChange(HttpServletRequest req, Model model) {
		System.out.println("/amountChange()");

		memberService.amountChange(req, model);
		memberService.cartList(req, model);
		
		return "member/cart";
	}
	//���̵� ã��
	@RequestMapping("idSearch")
	public String idSearch(HttpServletRequest req, Model model) {
		System.out.println("/idSearch()");

		return "member/idSearch";
	}
	@RequestMapping("idSearchPro")
	public String idSearchPro(HttpServletRequest req, Model model) {
		System.out.println("/idSearchPro()");
		
		memberService.idSearch(req, model);
		
		return "member/idSearch";
	}
	@RequestMapping("idView")
	public String idView(HttpServletRequest req, Model model) {
		System.out.println("/idView()");
		
		String id=req.getParameter("id");
		req.setAttribute("id", id);
		req.setAttribute("cnt", 3);
		
		return "member/idSearch";
	}
	//��й�ȣ ã��
	@RequestMapping("pwdSearch")
	public String pwdSearch(HttpServletRequest req, Model model) {
		System.out.println("/pwdSearch()");

		return "member/pwdSearch";
	}
	@RequestMapping("pwdSearchPro")
	public String pwdSearchPro(HttpServletRequest req, Model model) {
		System.out.println("/pwdSearchPro()");

		memberService.pwdSearch(req, model);
		
		return "member/pwdSearch";
	}
	@RequestMapping("pwdView")
	public String pwdView(HttpServletRequest req, Model model) {
		System.out.println("/pwdView()");

		String pwd=req.getParameter("pwd");
		req.setAttribute("pwd", pwd);
		req.setAttribute("cnt", 3);
		
		return "member/pwdSearch";
	}
	//member--------------------------------------------------------------------------------
	//������ �޴�-------------------------------------------------------------------------------
	//������ �޴�
	@RequestMapping("host")
	public String host(HttpServletRequest req, Model model) {
		System.out.println("/host()");
		
		int hostMain=1;		
		req.setAttribute("hostMain", hostMain);	
		hostService.hostMain(req, model);
		
		return "host/host";
	}
	//ȸ����� ����
	@RequestMapping("Hmember")
	public String Hmember(HttpServletRequest req, Model model) {
		System.out.println("/Hmember()");
		
		hostService.Hmember(req, model);
		
		return "host/host";
	}
	//ȸ������
	@RequestMapping("deleteMem")
	public String deleteMem(HttpServletRequest req, Model model) {
		System.out.println("/deleteMem()");
		
		hostService.deleteMem(req, model);
		hostService.Hmember(req, model);
		
		return "host/host";
	}
	//�����Ʈ ����
	@RequestMapping("HostBookList")
	public String HostBookList(HttpServletRequest req, Model model) {
		System.out.println("/HostBookList()");
		
		hostService.bookList(req, model);
		
		return "host/host";
	}
	//�ֹ� ����Ʈ ����
	@RequestMapping("orderList")
	public String orderList(HttpServletRequest req, Model model) {
		System.out.println("/orderList()");
		
		hostService.orderList(req, model);
		hostService.delivaryList(req, model);
		
		return "host/host";
	}
	//å ��� �߰�
	@RequestMapping("insertBook")
	public String insertBook(HttpServletRequest req, Model model) {
		System.out.println("/insertBook()");
		
		hostService.insertBook(req, model);
		hostService.bookList(req, model);
		
		return "host/host";
	}
	//å ����
	@RequestMapping("deleteBook")
	public String deleteBook(HttpServletRequest req, Model model) {
		System.out.println("/deleteBook()");
		
		hostService.deleteBook(req, model);
		hostService.bookList(req, model);
		
		return "host/host";
	}
	//å ����
	@RequestMapping("updateBook")
	public String updateBook(HttpServletRequest req, Model model) {
		System.out.println("/updateBook()");
		
		hostService.updateBook(req, model);
		hostService.bookList(req, model);
		
		return "host/host";
	}
	//��� ���
	@RequestMapping("Delivery")
	public String Delivery(HttpServletRequest req, Model model) {
		System.out.println("/Delivery()");
		//num,price,amount,title
		//����ϱ� Ŭ��->orderList=>refundList[���ѱ�] ->���+ ,��Ʈ�� �ǸŰ���+, �Ǹŵ� å ����+
		hostService.delivery(req, model);
		//delivary[1 or 0]
		hostService.orderList(req, model);
		hostService.delivaryList(req, model);
		
		return "host/host";
	}
	//ȯ��
	@RequestMapping("hostRefund")
	public String hostRefund(HttpServletRequest req, Model model) {
		System.out.println("/hostRefund()");

		hostService.refund(req, model);
		hostService.orderList(req, model);
		hostService.delivaryList(req, model);
		
		return "host/host";
	}
	//ȯ�ҿϷ� ��� ����
	@RequestMapping("deleteDel")
	public String deleteDel(HttpServletRequest req, Model model) {
		System.out.println("/deleteDel()");

		hostService.deleteDel(req, model);
		hostService.orderList(req, model);
		hostService.delivaryList(req, model);
		
		return "host/host";
	}
	//������ �޴�------------------------------------------------------------------------------
	//book---------------------------------------------------------------------------------
	//å���
		@RequestMapping("bookList")
		public String bookList(HttpServletRequest req, Model model) {
			System.out.println("/bookList()");
			
			bookService.bookList(req, model);
			
			return "bmsProject/books";
		}
		
		//�� ������
		@RequestMapping("detailPage")
		public String detailPage(HttpServletRequest req, Model model) {
			System.out.println("/detailPage()");
			
			bookService.serchBook(req, model);
			
			return "bmsProject/detailPage";
		}
		
		//�����ڰ� ȸ������ �ƴ��� �˻�
		@RequestMapping("DirectPro")
		public String DirectPro(HttpServletRequest req, Model model) {
			System.out.println("/DirectPro()");
			
			String title=req.getParameter("title");
			int amount=Integer.parseInt(req.getParameter("amount"));
			int price=Integer.parseInt(req.getParameter("price"));
			int code=Integer.parseInt(req.getParameter("code"));
			String image=req.getParameter("image");
			
			req.setAttribute("title", title);
			req.setAttribute("amount", amount);
			req.setAttribute("price", price);
			req.setAttribute("code", code);
			req.setAttribute("image", image);
			
			return "bmsProject/DirectPro";
		}
		
		//�� ���������� å �ٷα���
		@RequestMapping("DirectBy")
		public String DirectBy(HttpServletRequest req, Model model) {
			System.out.println("/DirectBy()");

			String title=req.getParameter("title");
			int amount=Integer.parseInt(req.getParameter("amount"));
			int price=Integer.parseInt(req.getParameter("price"));
			int code=Integer.parseInt(req.getParameter("code"));
			String image=req.getParameter("image");
			
			req.setAttribute("title", title);
			req.setAttribute("amount", amount);
			req.setAttribute("price", price);
			req.setAttribute("code", code);
			req.setAttribute("image", image);
			
			/* 1. ȸ���϶� [����������Ұ��/������������� �������]
			 * 		1)������������� �������
			 * 			code,amount,price,title
			 * 		2)����� ������ ���
			 * 			code,amount,price,title,postNum,addr1,addr2
			 * 2. �Խ�Ʈ�϶�
			 * 		code,amount,price,title,name,hp1,hp2,hp3,email1,email2
			 *  	,postNum,addr1,addr2
			 * 3. �����ؾ� �ϴ� ��(id,name,hp,email,addr,title,amount,price)
			 */
			bookService.DirectBy(req, model);
			
			bookService.serchBook(req, model);
			return "bmsProject/detailPage";
		}
	//book---------------------------------------------------------------------------------
	//�Խ���---------------------------------------------------------------------------------
		//���� ������
		@RequestMapping("list")
		public String list(HttpServletRequest req, Model model) {
			System.out.println("/list()");
			
			boardService.BoardList(req, model);
			
			return "board/boardList";
		}
		// ��������
		@RequestMapping("contentForm")
		public String contentForm(HttpServletRequest req, Model model) {
			System.out.println("/contentForm()");
			
			boardService.contentForm(req, model);
			
			return "board/contentForm";
		}
		// �ۼ��� ��������
		@RequestMapping("BDmodifyForm")
		public String BDmodifyForm(HttpServletRequest req, Model model) {
			System.out.println("/BDmodifyForm()");
			
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			
			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			
			return "board/modifyForm";
		}
		// �ۼ��� ��������
		@RequestMapping("BDmodifyView")
		public String BDmodifyView(HttpServletRequest req, Model model) {
			System.out.println("/BDmodifyView()");
			
			boardService.modifyView(req, model);
			
			return "board/modifyView";
		}
		// �ۼ��� ó��������
		@RequestMapping("BDmodifyPro")
		public String BDmodifyPro(HttpServletRequest req, Model model) {
			System.out.println("/BDmodifyPro()");
			
			boardService.modifyPro(req, model);
			
			return "board/modifyPro";
		}
		// �۾��� ��������
		@RequestMapping("writeForm")
		public String writeForm(HttpServletRequest req, Model model) {
			System.out.println("/writeForm()");
			
			//�����(�亯���� �ƴѰ��)
			int num=0;
			int ref=1;	//�׷�ȭ ���̵�
			int ref_step=0; //�ۼ���(��)
			int ref_level=0; //�۷���(�鿩����)
			
			//�亯��
			//contentForm.jsp���� get������� �ѱ� �� num, ref, ref_step, ref_level�� �޴´�.
			if(req.getParameter("num")!=null) {
				num=Integer.parseInt(req.getParameter("num"));
				ref=Integer.parseInt(req.getParameter("ref"));
				ref_step=Integer.parseInt(req.getParameter("ref_step"));
				ref_level=Integer.parseInt(req.getParameter("ref_level"));
				
			}
			req.setAttribute("num", num);
			req.setAttribute("ref", ref);
			req.setAttribute("ref_step", ref_step);
			req.setAttribute("ref_level", ref_level);
			
			return "board/modifyPro";
		}
		// �۾��� ó�� ������
		@RequestMapping("writePro")
		public String writePro(HttpServletRequest req, Model model) {
			System.out.println("/writePro()");
			
			boardService.writePro(req, model);
			
			return "board/writePro";
		}
		// �ۻ��� ��������
		@RequestMapping("deleteForm")
		public String deleteForm(HttpServletRequest req, Model model) {
			System.out.println("/deleteForm()");
			
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));

			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			
			return "board/deleteForm";
		}
		// �ۻ��� ó�� ������
		@RequestMapping("deletePro")
		public String deletePro(HttpServletRequest req, Model model) {
			System.out.println("/deletePro()");
			
			boardService.deletePro(req, model);
			
			return "board/deletePro";
		}
		//�˻�
		@RequestMapping("boardSerch")
		public String boardSerch(HttpServletRequest req, Model model) {
			System.out.println("/boardSerch()");
			
			boardService.BoardSerch(req, model);
			
			return "board/deletePro";
		}
		//�Խ���---------------------------------------------------------------------------------
}
