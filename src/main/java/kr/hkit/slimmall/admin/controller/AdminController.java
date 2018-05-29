package kr.hkit.slimmall.admin.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import kr.hkit.slimmall.admin.mapper.AdminMapper;
import kr.hkit.slimmall.admin.mapper.AdminVO;
import kr.hkit.slimmall.admin.mapper.CategoryVO;
import kr.hkit.slimmall.admin.mapper.MemberVO;
import kr.hkit.slimmall.admin.mapper.OrderVO;
import kr.hkit.slimmall.admin.mapper.ProductVO;

@SessionAttributes("adminVO")
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@ModelAttribute("adminVO")
	public AdminVO createAdminVO() {
		return new AdminVO();
	}
	
	@Autowired
	private AdminMapper adminMapper;

	@RequestMapping("/header.do")
	public String header(Model model) throws Exception {		
		return "admin/header";
	}
	
	@RequestMapping("/nav.do")
	public String nav(@ModelAttribute NavDTO navDTO, Model model) throws Exception {	
		model.addAttribute("menu", navDTO.getMenu());
		return "admin/nav";
	}
	
	@RequestMapping("/footer.do")
	public String footer(Model model) throws Exception {		
		return "admin/footer";
	}
	
	@RequestMapping("/main.do")
	public String main(@ModelAttribute AdminVO adminVO, Model model) throws Exception {		
		return "admin/main";
	}
	
	@RequestMapping("/memberList.do")
	public String memberList(Model model) throws Exception {
		List<MemberVO> memberVOs = adminMapper.selectMemberVOs();
		model.addAttribute("memberVOs", memberVOs);
		return "admin/memberList";
	}
	
	@RequestMapping("/memberAdd.do")
	public String memberAdd(Model model) throws Exception {
		List<MemberVO> memberVOs = adminMapper.selectMemberVOs();
		model.addAttribute("memberVOs", memberVOs);
		return "admin/memberAdd";
	}
	
	@RequestMapping("/addMember.do")
	public String addMember(
			@ModelAttribute AddMemberDTO addMemberDTO, Model model) throws Exception {
		String username = addMemberDTO.getUsername();
		String password = addMemberDTO.getPassword();
		String email = addMemberDTO.getEmail();
		String phone = addMemberDTO.getPhone();
		String address = addMemberDTO.getAddress();
			
		adminMapper.insertMemberVO(username, password, email, phone, address);
		
		return "redirect:/admin/memberList.do";
	}
	
	
	@RequestMapping("/deleteMembers.do")
	public String deleteMembers(DeleteMembersDTO deleteMembersDTO, Model model) throws Exception {		
		List<Integer> idxes = deleteMembersDTO.getIdxes();
		
		for(int idx : idxes) {
			adminMapper.deleteMember(idx);
		}
		return "redirect:/admin/memberList.do";
	}
	
	@RequestMapping("/adminList.do")
	public String adminList(@ModelAttribute AdminVO adminVO, Model model) throws Exception {	
		List<AdminVO> adminVOs = adminMapper.selectAdminVOs(adminVO);
		model.addAttribute("adminVOs", adminVOs);
		return "admin/adminList";
	}
	
	@RequestMapping("/adminAdd.do")
	public String adminAdd(@ModelAttribute AdminVO adminVO, Model model) throws Exception {		
		return "admin/adminAdd";
	}
	
	@RequestMapping("/adminSet.do")
	public String adminSet(@ModelAttribute AdminVO adminVO, Model model) throws Exception {	
		adminVO = adminMapper.selectAdminVO(adminVO);
		model.addAttribute("adminVO", adminVO);
		return "admin/adminSet";
	}
	
	@RequestMapping("/addAdmin.do")
	public String addAdmin(@ModelAttribute AdminVO adminVO, Model model) throws Exception {	
		adminMapper.insertAdminVO(adminVO);
		return "redirect:/admin/adminList.do";
	}
	
	@RequestMapping("/setAdmin.do")
	public String setAdmin(@ModelAttribute AdminVO adminVO, Model model) throws Exception {	
		adminMapper.updateAdminVO(adminVO);
		return "redirect:/admin/adminList.do";
	}
	
	@RequestMapping("/removeAdmins.do")
	public String removeAdmins(@ModelAttribute AdminVO adminVO, Model model) throws Exception {	
		String tableName = adminVO.getTableName();
		List<Integer> idxes = adminVO.getIdxes();
		
		for(int idx : idxes) {
			adminVO = new AdminVO();
			adminVO.setIdx(idx);
			adminVO.setTableName(tableName);;
			adminMapper.deleteAdminVO(adminVO);
		}
		return "redirect:/admin/adminList.do";
	}
	
	@RequestMapping("/orderList.do")
	public String orderList(Model model) throws Exception {
		List<OrderVO> orderVOs = adminMapper.selectOrderVOs();
		model.addAttribute("orderVOs", orderVOs);
		return "admin/orderList";
	}
	
	@RequestMapping("/productList.do")
	public String productList(Model model) throws Exception {
		List<ProductVO> productVOs = adminMapper.selectProductVOs();
		model.addAttribute("productVOs", productVOs);
		return "admin/productList";
	}
	
	@RequestMapping("/productAdd.do")
	public String productAdd(Model model) throws Exception {
		List<CategoryVO> categoryVOs = adminMapper.selectCategoryVOs();
		model.addAttribute("categoryVOs", categoryVOs);
		return "admin/productAdd";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(
			@ModelAttribute AddProductDTO addProductDTO, Model model) throws Exception {
		String name = addProductDTO.getName();
		String description = addProductDTO.getDescription();
		int price = addProductDTO.getPrice();
		int qty = addProductDTO.getQty();
		int categoryIdx = addProductDTO.getCategoryIdx();
		String imageName = null;
		
		MultipartFile imageFile = addProductDTO.getImageFile();
		imageName = imageFile.getOriginalFilename();
			
		
		// 이미지 파일를 C:\temp에 저장
       if(imageFile.isEmpty() == false) {
            byte[] bytes = imageFile.getBytes();
            Path path = Paths.get("C://temp//"  +  
            		imageFile.getOriginalFilename());
            Files.write(path, bytes);
        }
		
       // 이미지 이름을  DB에 저장
		adminMapper.insertProduct(
				name, description, price, qty, 
				categoryIdx, imageName);
		
		return "redirect:/admin/productList.do";
	}
	

}
