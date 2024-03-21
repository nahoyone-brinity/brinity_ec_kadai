package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
//@RequestMapping
public class ItemController {

	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	// 商品一覧表示
	@GetMapping("/items")
	public String index(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		// 全カテゴリー一覧を取得
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

		return "items";
	}
	
	//金額の安い順に並び替え
	@GetMapping(path="/items", params="sort=orderAsc")
	public String sortAsc(Model model) {	
		List<Item> itemList = itemRepository.findAllByOrderByPriceAsc();
		model.addAttribute("items", itemList);
		return "items";
	}
	
	//金額の高い順に並び替え
	@GetMapping(path="/items", params="sort=orderDesc")
	public String sortDesc(Model model) {	
		List<Item> itemList = itemRepository.findAllByOrderByPriceDesc();
		model.addAttribute("items", itemList);
		return "items";
	}

	//クリック時に検索結果を取得
	@PostMapping("/items")
	public String result(
			@RequestParam(name = "name", defaultValue = "") String name,
			Model model) {

		List<Item> itemList = null;
		if (name == null || name.length() == 0) {
			return "redirect:items";

		} else {
			// itemsテーブルを商品名を指定して結果を取得
			itemList = itemRepository.findByNameContaining(name);
		}

		model.addAttribute("items", itemList);

		return "items";
	}

}
