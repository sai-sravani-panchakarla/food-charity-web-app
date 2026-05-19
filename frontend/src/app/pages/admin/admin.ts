import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../../services/food';
import { DonorService } from '../../services/donor';
import { ExpenseService } from '../../services/expense';

@Component({
  selector: 'app-admin',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.scss'
})
export class Admin {
  activeTab = 'food';
  successMsg = '';

  months = ['January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December'];

  foodItem = { itemName: '', description: '', month: '', servedYear: 2024, servedTo: 'Pregnant women', cookedBy: '' };
  donor = { donorName: '', amount: 0, donatedDate: '', message: '', month: '', donatedYear: 2024 };
  expense = { itemName: '', amount: 0, category: '', month: '', expenseYear: 2024, expenseDate: '', notes: '' };

  constructor(
    private foodService: FoodService,
    private donorService: DonorService,
    private expenseService: ExpenseService
  ) {}

  setTab(tab: string) { this.activeTab = tab; this.successMsg = ''; }

  addFood() {
    this.foodService.add(this.foodItem).subscribe({
      next: () => {
        this.successMsg = 'Menu item added successfully!';
        this.foodItem = { itemName: '', description: '', month: '', servedYear: 2024, servedTo: 'Pregnant women', cookedBy: '' };
      }
    });
  }

  addDonor() {
    this.donorService.add(this.donor).subscribe({
      next: () => {
        this.successMsg = 'Donor added successfully!';
        this.donor = { donorName: '', amount: 0, donatedDate: '', message: '', month: '', donatedYear: 2024 };
      }
    });
  }

  addExpense() {
    this.expenseService.add(this.expense).subscribe({
      next: () => {
        this.successMsg = 'Expense added successfully!';
        this.expense = { itemName: '', amount: 0, category: '', month: '', expenseYear: 2024, expenseDate: '', notes: '' };
      }
    });
  }
}
