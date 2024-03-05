package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/login")
public class AccountAdminController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	// 管理者ログイン画面初期表示
	@GetMapping
	public String index() {

		// セッション破棄
		session.invalidate();
		return "admin/login";
	}

	// 管理者ログイン処理
	@PostMapping
	public String login(
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			Model model) {

		// ログインチェック
		if (!name.equals("admin") || !password.equals("himitu")) {
			model.addAttribute("message", "ユーザ名とパスワードが一致しませんでした");
			return "admin/login";
		}

		account.setName("admin");
		return "redirect:/admin/items";

	}
}
