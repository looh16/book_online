package org.book_online.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.book_online.daos.ProductDAO;
import org.book_online.models.BookType;
import org.book_online.models.FileSaver;
import org.book_online.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional 
@RequestMapping("/produtos") 
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired 
	private FileSaver fileSaver;

	@RequestMapping("/form") 
	public ModelAndView form(){ 
		ModelAndView modelAndView = new ModelAndView("products/form"); 
		modelAndView.addObject("types", BookType.values()); 
		return modelAndView; 
	}
	
	@RequestMapping(method=RequestMethod.GET) 
	public ModelAndView list(){ 
		ModelAndView modelAndView = new ModelAndView("products/list"); 
		modelAndView.addObject("products", productDAO.list()); 
		return modelAndView; 
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		System.out.println(summary.getName()+ ";" 
	    +summary.getOriginalFilename()); 
		
		if(bindingResult.hasErrors()){ return form(); 
		} 
		String webPath = fileSaver.write("uploaded-images",summary); 
		product.setSummaryPath(webPath); 
		
		productDAO.save(product); 
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso"); 
		return new ModelAndView("redirect:produtos"); 
	}



	
}
