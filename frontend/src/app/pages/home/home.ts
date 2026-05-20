import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [RouterLink, CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home {
  showHospitalModal = false;
  showFamilyModal = false;
  showSaiBabaModal = false;
  showDonateModal = false;

  familyMembers = [
    { name: 'Father', role: 'Initiative Lead', emoji: '👨', desc: 'Coordinates the entire food service every month' },
    { name: 'Mother', role: 'Head Cook', emoji: '👩', desc: 'Prepares the main dishes with love and care' },
    { name: 'Sister', role: 'Volunteer', emoji: '👧', desc: 'Helps with serving and distribution' },
    { name: 'Brother', role: 'Volunteer', emoji: '👦', desc: 'Manages logistics and supplies' },
    { name: 'Grandmother', role: 'Recipe Guide', emoji: '👵', desc: 'Shares traditional recipes and wisdom' },
    { name: 'Sravani', role: 'Tech & Coordinator', emoji: '👩‍💻', desc: 'Built this website and coordinates volunteers' },
  ];

  openModal(type: string) {
    if (type === 'hospital') this.showHospitalModal = true;
    if (type === 'family') this.showFamilyModal = true;
    if (type === 'sai') this.showSaiBabaModal = true;
    if (type === 'donate') this.showDonateModal = true;
  }

  closeAll() {
    this.showHospitalModal = false;
    this.showFamilyModal = false;
    this.showSaiBabaModal = false;
    this.showDonateModal = false;
  }
}
