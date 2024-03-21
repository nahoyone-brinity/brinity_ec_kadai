package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		//session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			Model model) {
		// 名前が空の場合にエラーとする
		if (name == null || name.length() == 0) {
			model.addAttribute("message", "入力エラー");
			return "login";
		} else if (password == null || password.length() == 0) {
			model.addAttribute("message", "入力エラー");
			return "login";
		}

		// セッション管理されたアカウント情報に名前とパスワードをセット
		account.setName(name);
		account.setPassword(password);

		// 「/items」へのリダイレクト
		return "redirect:/items";
	}
	

	//新規会員登録
	@PostMapping(path = "/login", params = "create_name")
	public String create(
			@RequestParam("create_name") String name,
			@RequestParam("create_password") String password,
			@RequestParam("create_email") String email,
			Model model) {

		//空欄時にエラー
		if (name == null || name.length() == 0) {
			model.addAttribute("message", "入力エラー");
			return "/login";
		} else if (password == null || password.length() == 0) {
			model.addAttribute("message", "入力エラー");
			return "/login";
		} else if (email == null || email.length() == 0) {
			model.addAttribute("message", "入力エラー");
			return "/login";
		}

		account.setName(name);
		account.setPassword(password);
		account.setEmail(email);

		User user = new User(name, password, email);
		userRepository.save(user);

		return "redirect:/items";
	}
}
