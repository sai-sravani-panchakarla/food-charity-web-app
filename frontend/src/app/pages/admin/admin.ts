import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../../services/food';
import { DonorService } from '../../services/donor';
import { ExpenseService } from '../../services/expense';
import { ToastService } from '../../services/toast';

@Component({
  selector: 'app-admin',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.scss'
})
export class Admin {
  activeTab = 'food';
  loading = false;

  months = ['January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December'];

  foodItem = { itemName: '', description: '', month: '', servedYear: 2024, servedTo: 'Pregnant women', cookedBy: '' };
  donor = { donorName: '', amount: 0, donatedDate: '', message: '', month: '', donatedYear: 2024 };
  expense = { itemName: '', amount: 0, category: '', month: '', expenseYear: 2024, expenseDate: '', notes: '' };

  foodErrors: any = {};
  donorErrors: any = {};
  expenseErrors: any = {};

  constructor(
    private foodService: FoodService,
    private donorService: DonorService,
    private expenseService: ExpenseService,
    private toast: ToastService
  ) {}

  setTab(tab: string) {
    this.activeTab = tab;
    this.foodErrors = {};
    this.donorErrors = {};
    this.expenseErrors = {};
  }

  validateFood(): boolean {
    this.foodErrors = {};
    if (!this.foodItem.itemName.trim())
      this.foodErrors.itemName = 'Food item name is required';
    else if (this.foodItem.itemName.length < 2)
      this.foodErrors.itemName = 'Item name must be at least 2 characters';
    if (!this.foodItem.description.trim())
      this.foodErrors.description = 'Description is required';
    if (!this.foodItem.month)
      this.foodErrors.month = 'Month is required';
    if (!this.foodItem.cookedBy.trim())
      this.foodErrors.cookedBy = 'Cooked by is required';
    return Object.keys(this.foodErrors).length === 0;
  }

  validateDonor(): boolean {
    this.donorErrors = {};
    if (!this.donor.donorName.trim())
      this.donorErrors.donorName = 'Donor name is required';
    if (!this.donor.amount || this.donor.amount <= 0)
      this.donorErrors.amount = 'Amount must be greater than 0';
    if (!this.donor.donatedDate)
      this.donorErrors.donatedDate = 'Donated date is required';
    if (!this.donor.month)
      this.donorErrors.month = 'Month is required';
    return Object.keys(this.donorErrors).length === 0;
  }

  validateExpense(): boolean {
    this.expenseErrors = {};
    if (!this.expense.itemName.trim())
      this.expenseErrors.itemName = 'Item name is required';
    if (!this.expense.amount || this.expense.amount <= 0)
      this.expenseErrors.amount = 'Amount must be greater than 0';
    if (!this.expense.category.trim())
      this.expenseErrors.category = 'Category is required';
    if (!this.expense.month)
      this.expenseErrors.month = 'Month is required';
    if (!this.expense.expenseDate)
      this.expenseErrors.expenseDate = 'Expense date is required';
    return Object.keys(this.expenseErrors).length === 0;
  }

  addFood() {
    if (!this.validateFood()) {
      this.toast.warning('Please fix the errors before submitting!');
      return;
    }
    this.loading = true;
    this.foodService.add(this.foodItem).subscribe({
      next: () => {
        this.toast.success('🍛 Food item added successfully!');
        this.foodItem = { itemName: '', description: '', month: '', servedYear: 2024, servedTo: 'Pregnant women', cookedBy: '' };
        this.foodErrors = {};
        this.loading = false;
      },
      error: (err) => {
        const msg = err.error?.errors?.[0] || 'Failed to add food item!';
        this.toast.error(msg);
        this.loading = false;
      }
    });
  }

  addDonor() {
    if (!this.validateDonor()) {
      this.toast.warning('Please fix the errors before submitting!');
      return;
    }
    this.loading = true;
    this.donorService.add(this.donor).subscribe({
      next: () => {
        this.toast.success('💝 Donor added successfully!');
        this.donor = { donorName: '', amount: 0, donatedDate: '', message: '', month: '', donatedYear: 2024 };
        this.donorErrors = {};
        this.loading = false;
      },
      error: (err) => {
        const msg = err.error?.errors?.[0] || 'Failed to add donor!';
        this.toast.error(msg);
        this.loading = false;
      }
    });
  }

  addExpense() {
    if (!this.validateExpense()) {
      this.toast.warning('Please fix the errors before submitting!');
      return;
    }
    this.loading = true;
    this.expenseService.add(this.expense).subscribe({
      next: () => {
        this.toast.success('🛒 Expense added successfully!');
        this.expense = { itemName: '', amount: 0, category: '', month: '', expenseYear: 2024, expenseDate: '', notes: '' };
        this.expenseErrors = {};
        this.loading = false;
      },
      error: (err) => {
        const msg = err.error?.errors?.[0] || 'Failed to add expense!';
        this.toast.error(msg);
        this.loading = false;
      }
    });
  }
}
