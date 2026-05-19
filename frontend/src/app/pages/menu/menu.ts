import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../../services/food';

@Component({
  selector: 'app-menu',
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.html',
  styleUrl: './menu.scss'
})
export class Menu implements OnInit {
  menuItems: any[] = [];
  filteredItems: any[] = [];
  selectedMonth = 'All';
  selectedYear = 2024;
  loading = false;

  months = ['All', 'January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December'];
  years = [2024, 2025, 2026];

  constructor(private foodService: FoodService) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.loading = true;
    this.foodService.getAll().subscribe({
      next: (data) => {
        this.menuItems = data;
        this.filteredItems = data;
        this.loading = false;
      },
      error: () => { this.loading = false; }
    });
  }

  filterMenu() {
    if (this.selectedMonth === 'All') {
      this.loadAll();
    } else {
      this.loading = true;
      this.foodService.getByMonthAndYear(this.selectedMonth, this.selectedYear).subscribe({
        next: (data) => {
          this.filteredItems = data;
          this.loading = false;
        },
        error: () => { this.loading = false; }
      });
    }
  }
}
