import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DonorService } from '../../services/donor';
import { ExpenseService } from '../../services/expense';

@Component({
  selector: 'app-donors',
  imports: [CommonModule, FormsModule],
  templateUrl: './donors.html',
  styleUrl: './donors.scss'
})
export class Donors implements OnInit {
  donors: any[] = [];
  expenses: any[] = [];
  totalDonations = 0;
  totalExpenses = 0;
  selectedMonth = 'All';
  loading = false;

  months = ['All', 'January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December'];

  constructor(
    private donorService: DonorService,
    private expenseService: ExpenseService
  ) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.loading = true;
    this.donorService.getAll().subscribe(data => { this.donors = data; });
    this.expenseService.getAll().subscribe(data => { this.expenses = data; });
    this.donorService.getTotal().subscribe(total => {
      this.totalDonations = total || 0;
    });
    this.expenseService.getTotal().subscribe(total => {
      this.totalExpenses = total || 0;
      this.loading = false;
    });
  }

  filterByMonth() {
    if (this.selectedMonth === 'All') {
      this.loadAll();
    } else {
      this.donorService.getByMonth(this.selectedMonth).subscribe(data => {
        this.donors = data;
      });
      this.expenseService.getByMonth(this.selectedMonth).subscribe(data => {
        this.expenses = data;
      });
    }
  }

  get balance() {
    return this.totalDonations - this.totalExpenses;
  }
}
