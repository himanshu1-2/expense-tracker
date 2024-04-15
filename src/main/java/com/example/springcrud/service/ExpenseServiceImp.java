package com.example.springcrud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springcrud.entity.Expense;

public interface ExpenseServiceImp {


	//Page<Expense>
	Page<Expense> getAllExpensesOfUser(Pageable page);
	
	List<Expense> getAllExpenses(Pageable page);
	
	Expense getExpenseById(Long id);
	
	
	
	void deleteExpenseById(Long id);

	Expense saveExpenseDetails(Expense expense);
	
	Expense updateExpenseDetails(Long id, Expense expense);
	
	Page<Expense> findExpenserBykeyword(String keyword, Pageable page);
	
}
