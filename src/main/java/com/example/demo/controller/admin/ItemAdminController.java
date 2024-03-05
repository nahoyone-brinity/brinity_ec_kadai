package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
@RequestMapping("/admin/items") // @RequestMappingを利用することで@GetMappingや@PostMappingのURLを省略できます
public class ItemAdminController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	// 商品一覧表示
	@GetMapping
	public String index(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (categoryId == null) {
			itemList = itemRepository.findAll();
		} else {
			// itemsテーブルをカテゴリーIDを指定して一覧を取得
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("items", itemList);

		return "admin/items";
	}

	// 新規登録画面の表示
	@GetMapping("/add")
	public String create(Model model) {

		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		return "admin/addItem";
	}

	// 新規登録処理
	@PostMapping("/add")
	public String store(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			Model model) {

		// Itemオブジェクトの生成
		Item item = new Item(categoryId, name, price);
		// itemテーブルへの反映（INSERT）
		itemRepository.save(item);
		// 「/items」にGETでリクエストし直せ（リダイレクト）
		return "redirect:/admin/items";
	}

	// 更新画面表示
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {

		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// itemsテーブルをID（主キー）で検索
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);

		return "admin/editItem";
	}

	// 更新処理
	@PostMapping("/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			Model model) {

		// Itemオブジェクトの生成
		Item item = new Item(id, categoryId, name, price);
		// itemsテーブルへの反映（UPDATE）
		itemRepository.save(item);
		// 「/items」にGETでリクエストし直せ（リダイレクト）
		return "redirect:/admin/items";
	}

	// 削除処理
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, Model model) {

		// itemsテーブルから削除（DELETE）
		itemRepository.deleteById(id);
		// 「/items」にGETでリクエストし直せ（リダイレクト）
		return "redirect:/admin/items";
	}
}
