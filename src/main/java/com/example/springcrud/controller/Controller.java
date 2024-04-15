package com.example.springcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcrud.entity.Expense;
import com.example.springcrud.entity.Response;
import com.example.springcrud.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
public class Controller {

	
	 @Autowired
	    private ExpenseService expenseService;

		@GetMapping("/userExpenses")
		public Response getAllExpensesOfUser(Pageable page) {
			 Page<Expense> data = expenseService. getAllExpensesOfUser(page);
			 return new Response<>("sucessfully called back",data.toList());
		}
		
//		@GetMapping("/expenses")
//		public Response getAllExpenses(Pageable page) {
//			 List<Expense> data = expenseService. getAllExpenses(page);
//			 return new Response<>("sucessfully called back",data);
//		}	
		
		@GetMapping("/expenses/{id}")
		public Expense getExpenseById(@PathVariable Long id) {
			return expenseService.getExpenseById(id);
		}
		
		@ResponseStatus(value = HttpStatus.NO_CONTENT)
		@DeleteMapping("/expenses")
		public void deleteExpenseById(@RequestParam Long id) {
			expenseService.deleteExpenseById(id);
		}
		
		@ResponseStatus(value = HttpStatus.CREATED)
		@PostMapping("/expenses")
		public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
			return expenseService.saveExpenseDetails(expense);
		}
		
		
		@PutMapping("/expenses/{id}")
		public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
			return expenseService.updateExpenseDetails(id, expense);
		}

		@GetMapping("/expenses/name")
		public Response getExpenseByKeyword(@RequestParam String keyword,Pageable page) {
			Page<Expense> data =  expenseService.findExpenserBykeyword(keyword,page);
			return new Response<>("sucessfully called back",data.toList());
		}
}
