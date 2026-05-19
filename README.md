# 🍱 Hospital Food Charity Web App

A full stack microservices web application to manage monthly food 
service for pregnant women at a government hospital.

## About the Initiative
Every month, our family along with generous neighbors cook and serve 
nutritious meals to pregnant women visiting the government hospital 
for their prenatal checkups. This app tracks the monthly menu, 
donor contributions, and expenses transparently.

## Tech Stack
- **Backend:** Java Spring Boot, Spring Cloud Gateway, Spring Data JPA
- **Frontend:** Angular 21, TypeScript, SCSS
- **Database:** MySQL
- **Architecture:** Microservices

## Microservices
| Service | Port | Description |
|---|---|---|
| Food Service | 8081 | Manages monthly food menu |
| Donor Service | 8082 | Tracks donor contributions |
| Expense Service | 8083 | Tracks monthly expenses |
| API Gateway | 8080 | Routes all API requests |
| Angular Frontend | 4200 | User interface |

## Features
- Monthly food menu display with filters
- Donor list with total contributions
- Expense tracker with monthly breakdown
- Admin panel to add data
- Fully responsive design
