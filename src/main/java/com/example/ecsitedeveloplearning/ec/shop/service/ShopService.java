package com.example.ecsitedeveloplearning.ec.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecsitedeveloplearning.ec.shop.model.Category;
import com.example.ecsitedeveloplearning.ec.shop.model.Product;
import com.example.ecsitedeveloplearning.ec.shop.repository.CategoryRepository;
import com.example.ecsitedeveloplearning.ec.shop.repository.ProductRepository;

@Service
public class ShopService {

	// イメージPath
	@Value("${upload.image.path}")
	private String uploadImagePath;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	// 商品リスト取得
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	// カテゴリ取得
	public List<Category> findCategories() {
		return categoryRepository.findAll();
	}

	// 商品登録
	public void register(Product product, MultipartFile image) {
		String imagePath = saveImageFile(image);
		if (imagePath.length() > 0) {
			product.setImagePath(imagePath);
			productRepository.save(product);
		}
	}
	
	// 商品取得
	public Product findProductById(long productId) {
		Product product = productRepository.findById(productId).get();
		return product;
	}

	//　イメージをファイルに保存する
	private String saveImageFile(MultipartFile image) {
		
		String imagePath = "";
		try {
			// Image Info
			String originFilename = image.getOriginalFilename();
//			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());

			// Save Image File
			imagePath = genSaveFileName(originFilename);

			System.out.println("originFilename : " + originFilename);
			System.out.println("saveFileName : " + imagePath);

			writeFile(image, imagePath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return imagePath;
	}

	// ファイル名生成
	private String genSaveFileName(String imageName) {
		Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        
        String format = sdf.format(cl.getTime());
		String fileName = "";
		fileName += format;
		fileName += imageName;

		return fileName;
	}

	// ファイル保存
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		boolean result = false;
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(uploadImagePath + "/" + saveFileName);
		fos.write(data);
		fos.close();
		return result;
	}

	// 商品修正
	public void update(Product product, MultipartFile image) {
		Product originalProduct = productRepository.findById(product.getId()).get();
		
		File originalImage = new File(uploadImagePath + "/" + originalProduct.getImagePath());
		originalImage.delete();
		
		String imagePath = saveImageFile(image);
		if (imagePath.length() > 0) {
			product.setImagePath(imagePath);
			productRepository.save(product);
		}
	}

	// 商品削除
	public void delete(long productId) {
		Product originalProduct = productRepository.findById(productId).get();
		File originalImage = new File(uploadImagePath + "/" + originalProduct.getImagePath());
		originalImage.delete();
		productRepository.deleteById(productId);
	}

	public List<Product> findAllByCategory(int id) {
		List<Product> products = productRepository.findByCategoryId(new Category(id, null));
		return products;
	}
	
}
