package com.example.springcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springcrud.entity.Expense;
import com.example.springcrud.exceptions.ResourceNotFoundException;
import com.example.springcrud.repository.ExpenseRepository;

@Service
public class ExpenseService implements ExpenseServiceImp {

	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private UserService userService;

	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> expense = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Expense is not found for the id "+id);
	}

	@Override
	public void deleteExpenseById(Long id) {
		expenseRepo.deleteById(id);
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		expense.setUser(userService.getLoggedInUser());
		return expenseRepo.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		return expenseRepo.save(existingExpense);
	}

	@Override
	public List<Expense> getAllExpenses(Pageable page) {
		 return expenseRepo.findAll();
		

	}
	
//	@Override
//	public  Page <Expense> getAllExpenses(Pageable page) {
//		 Page <Expense> data = expenseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
//		 
//		 //Response response = new Response<>("sucessfully called back",data);
//		//return response;
//
//	}

	

	@Override
	public Page <Expense> findExpenserBykeyword(String keyword, Pageable page) {
		return  expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page);
		
	}

	@Override
	public Page<Expense> getAllExpensesOfUser(Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findByUserId(userService.getLoggedInUser().getId(), page);
	}
	

	
}

