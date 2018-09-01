package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import service.TestDaoService;

import entity.Bill;
import entity.Param;
import entity.Provider;
import entity.Role;
import entity.User;
@Controller
public class IndexController {
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	TestDaoService helloSpring = (TestDaoService) context.getBean("testDaoService") ;
	//�������
	@RequestMapping("LoginServlet")
	public String LoginServlet(HttpServletRequest request){
		User user1 = new User();
		user1.setUserCode(request.getParameter("userCode"));
		user1.setUserPassword(request.getParameter("userPassword"));
		User user2 =helloSpring.SeveUser(user1);
			
		if(user2!=null){
			request.getSession().setAttribute("userSession", user2);
			return "frame";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("UserList")
	public String UserList(HttpServletRequest request){
		List<User> list = new ArrayList<User>();
		String userName = request.getParameter("queryname");
		String userRole = request.getParameter("queryUserRole");
		String pageIndex = request.getParameter("pageIndex");
		Param param = new Param();
		list = new TestDaoService().SeveList(userName,userRole,null,null);
		param.setTotalCount(list.size());
		param.setTotalPageCount(list.size()%10==0?list.size()/10:list.size()/10+1);
		if(pageIndex !=null){
			param.setCurrentPageNo(Integer.valueOf(pageIndex));
		}else{
			param.setCurrentPageNo(1);
		}
		list = new TestDaoService().SeveList(userName,userRole,(param.getCurrentPageNo()-1)*10,10);
		request.setAttribute("totalCount", param.getTotalCount());
		request.setAttribute("currentPageNo", param.getCurrentPageNo());
		request.setAttribute("totalPageCount", param.getTotalPageCount());
		request.setAttribute("queryUserName", userName);
		request.setAttribute("queryUserRole", userRole);
		List<Role> roleList = new TestDaoService().SeveRole();
		request.setAttribute("roleList", roleList);
		request.setAttribute("userList", list);
		return "userlist";
	}
	//����ҳ��
	@RequestMapping("blist")
	public String blist(HttpServletRequest request){
		List<Bill> billList = new ArrayList<Bill>();
		String queryProductName = request.getParameter("queryProductName");
		String queryProviderId = request.getParameter("queryProviderId");
		String queryIsPayment = request.getParameter("queryIsPayment");
		String pageIndex = request.getParameter("pageIndex");
		
		Param param = new Param();
		billList = new TestDaoService().QueryBill(queryProductName,queryProviderId,queryIsPayment,null,null);
		param.setTotalCount(billList.size());
		param.setTotalPageCount(billList.size()%10==0?billList.size()/10:billList.size()/10+1);
		if(pageIndex !=null){
			param.setCurrentPageNo(Integer.valueOf(pageIndex));
		}else{
			param.setCurrentPageNo(1);
		}
		billList = new TestDaoService().QueryBill(queryProductName,queryProviderId,queryIsPayment,
				(param.getCurrentPageNo()-1)*10,10);
		request.setAttribute("totalCount", param.getTotalCount());
		request.setAttribute("currentPageNo", param.getCurrentPageNo());
		request.setAttribute("totalPageCount", param.getTotalPageCount());
		request.setAttribute("queryProductName", queryProductName); 
		request.setAttribute("queryProviderId", queryProviderId);
		request.setAttribute("queryIsPayment", queryIsPayment);
		List<Provider> providerList = new TestDaoService().QueryProvider();
		request.setAttribute("providerList", providerList);
		request.setAttribute("billList", billList);
		return "billlist";
	}
	@RequestMapping(value = "/upload")
	//myfiles对应表单上的name="myfiles"
	public String upload(@ModelAttribute User user,
			@RequestParam MultipartFile[] myfiles, HttpServletRequest request)
			throws IOException {
		System.out.println("upload........................");
		
		String realPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				System.out.println("文件长度: " + myfile.getSize() + "B");
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
			
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(realPath, myfile.getOriginalFilename()));
			}
		}
		// return  "forward:/dept/list2";  //转发
		// return "redirect:/user/list"; //重定向
		return "billlist";
	}
	
	@RequestMapping("/download")
    public String downloadFile(@RequestParam("fileName") String fileName,
            HttpServletRequest request, HttpServletResponse response) {
		System.out.println("fileName="+fileName);
        if (fileName != null) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {              
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
           }
        }
        return null;
    }
}
