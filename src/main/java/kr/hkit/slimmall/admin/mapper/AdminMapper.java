package kr.hkit.slimmall.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface AdminMapper {
	
	public int insertAdminVO(AdminVO adminVO);
	public List<AdminVO> selectAdminVOs(AdminVO adminVO);
	public AdminVO selectAdminVO(AdminVO adminVO);
	public int updateAdminVO(AdminVO adminVO);
	public int deleteAdminVO(AdminVO adminVO);
	
	public List<MemberVO> selectMemberVOs();
	public int deleteMember(int idx);
	public List<OrderVO> selectOrderVOs();
	public int insertMemberVO(
			@Param("username") String username, 
			@Param("password") String password, 
			@Param("email") String email, 
			@Param("phone") String phone, 
			@Param("address") String address);
	
	public int insertProduct(
			@Param("name") String name, 
			@Param("description") String description, 
			@Param("price") int price, 
			@Param("qty") int qty, 
			@Param("categoryIdx") int categoryIdx, 
			@Param("imageName") String imageName);
	
	public List<CategoryVO> selectCategoryVOs();
	public List<ProductVO> selectProductVOs();
	
}
