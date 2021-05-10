package com.example.ecsitedeveloplearning.ec.shop.web;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.ecsitedeveloplearning.ec.shop.model.Category;
import com.example.ecsitedeveloplearning.ec.shop.model.Product;
import com.example.ecsitedeveloplearning.ec.shop.service.ShopService;

@Controller
@RequestMapping(path="/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@Value("${upload.image.path}")
	private String uploadImagePath;
	
	// TOP 画面
	@GetMapping("/top")
	public ModelAndView viewTop() {
		ModelAndView mv = new ModelAndView("shop/top");
		List<Product> products = shopService.findAllByCategory(1);
		List<Category> categories = shopService.findCategories();
		mv.addObject("categories", categories);
		mv.addObject("products", products);
		return mv;
	}
	
	// TOP 画面
	@GetMapping("/top/{categoryId}")
	public ModelAndView viewTopByCategory(@PathVariable int categoryId) {
		ModelAndView mv = new ModelAndView("shop/top");
		List<Product> products = shopService.findAllByCategory(categoryId);
		List<Category> categories = shopService.findCategories();
		mv.addObject("categories", categories);
		mv.addObject("products", products);
		return mv;
	}
	
	// 商品登録画面
	@GetMapping("/product")
	public ModelAndView viewRegisterProduct() {
		ModelAndView mv = new ModelAndView("shop/registerProduct");
		List<Category> categories = shopService.findCategories();
		mv.addObject("categories", categories);
		
		// Create Directory
		File dir = new File(uploadImagePath);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
		return mv;
	}
	
	// 商品登録
	@PostMapping("/product")
	public String insertProduct(
							@RequestParam("name") String name,
							@RequestParam("category") int category,
							@RequestParam("price") int price,
							@RequestParam("description") String description,
		      				@RequestParam("image") MultipartFile image) {
		
		Product product = new Product();
		product.setName(name);
		product.setCategoryId(new Category(category, null));
		product.setPrice(price);
		product.setDescription(description);
		
		shopService.register(product, image);
		return "redirect:/shop/top";
	}
	
	// 商品詳細画面
	@GetMapping("/product/{productId}")
	public ModelAndView viewProduct(@PathVariable long productId) {
		ModelAndView mv = new ModelAndView("shop/viewProduct");
		Product product = shopService.findProductById(productId);
		mv.addObject("product", product);
		return mv;
	}
	
	// 商品修正画面
	@GetMapping("/update/product/{productId}")
	public ModelAndView viewUpdateProduct(@PathVariable long productId) {
		ModelAndView mv = new ModelAndView("shop/updateProduct");
		Product product = shopService.findProductById(productId);
		List<Category> categories = shopService.findCategories();
		mv.addObject("categories", categories);
		mv.addObject("product", product);
		return mv;
	}
	
	// 商品修正
	@PostMapping("/update/product/{productId}")
	public String updateProduct(
							@PathVariable long productId,
							@RequestParam("name") String name,
							@RequestParam("category") int category,
							@RequestParam("price") int price,
							@RequestParam("description") String description,
		      				@RequestParam("image") MultipartFile image) {
		
		Product product = new Product();
		product.setId(productId);
		product.setName(name);
		product.setCategoryId(new Category(category, null));
		product.setPrice(price);
		product.setDescription(description);
		
		shopService.update(product, image);
		return "redirect:/shop/top";
	}
	
	// 商品削除
	@PostMapping("/delete/product/{productId}")
	public String deleteProduct(@PathVariable long productId) {
		System.out.println("productId : " + productId);
		shopService.delete(productId);
		return "redirect:/shop/top";
	}
	
}
