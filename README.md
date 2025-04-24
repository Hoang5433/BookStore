# BookStore Management System

A robust Java desktop application for managing bookstore operations, including inventory, sales, promotions, and reporting. The system features a modern GUI, role-based access, and integration with Excel/PDF for data import/export.

---

## 📖 Introduction

The BookStore Management System streamlines bookstore workflows, from inventory and customer management to order processing and analytics. Built with Java Swing, it provides an intuitive interface for staff and administrators.

---

## ✨ Features

- **Product Management:** Add, edit, delete, and search books by title, author, genre, and publisher.
- **Customer Management:** Track customer profiles, contact info, and purchase history.
- **Order Management:** Create, update, and track orders; generate invoices and receipts.
- **Promotion Management:** Manage discount codes and promotional campaigns.
- **Employee Management:** Add and manage staff accounts with role-based permissions.
- **Statistics & Reporting:** Generate sales, inventory, and promotion reports; export to Excel/PDF.
- **Data Import/Export:** Import/export data using Excel files; generate PDF invoices.
- **Secure Authentication:** Login system with roles (admin, staff).
- **User-Friendly GUI:** Built with Java Swing for cross-platform compatibility.

---

## 🗄️ Database Design

The system uses a relational database (e.g., MySQL). Key tables include:

- **SANPHAM:** Book details (title, author, genre, price, stock, etc.)
- **KHACHHANG:** Customer information.
- **DONHANG:** Orders and sales.
- **CHITIETDONHANG:** Order line items.
- **NHANVIEN:** Employee records.
- **TAIKHOAN:** User accounts and roles.
- **KHUYENMAI / CHITIETKHUYENMAI:** Promotions and discount codes.
- **THELOAI, TACGIA, NHAXUATBAN:** Book categories, authors, publishers.

See [`quanlycuahangsach.sql`](quanlycuahangsach.sql) for full schema.

---

## 📁 Project Structure

- `src/` — Java source code (BUS, DAO, DTO, GUI, IO, etc.)
- `AphachePOI/`, `iText/` — External libraries for Excel/PDF.
- `quanlycuahangsach.sql` — Database schema and sample data.

---

## ⚙️ Installation

### Prerequisites

- Java JDK 11 or higher
- MySQL or compatible RDBMS
- Java IDE (e.g., VS Code, IntelliJ, Eclipse)
- [Apache POI](https://poi.apache.org/) and [iText](https://itextpdf.com/) libraries

### Steps

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   ```
2. **Import the project** into your Java IDE.
3. **Install dependencies:**  
   Add Apache POI and iText JARs to your project's classpath.
4. **Set up the database:**
   - Create a database (e.g., `quanlycuahangsach`).
   - Import [`quanlycuahangsach.sql`](quanlycuahangsach.sql).
   - Update database connection settings in [`Database.java`](src/Database/Database.java).
5. **Build and run:**
   - Run `main.java` in [`src/quanlycuahangsach/main.java`](src/quanlycuahangsach/main.java).
   - Login with a valid account.

---

## 🧪 Testing

- Unit tests can be added under the `test/` directory (if available).
- Manual testing via the GUI is recommended for most features.

---

## 📦 Dependencies

- [Apache POI](https://poi.apache.org/) — Excel import/export
- [iText](https://itextpdf.com/) — PDF generation
- MySQL Connector/J — Database connectivity

---

## 🤝 Contributing

Pull requests and bug reports are welcome! Please open an issue or submit a PR.

---

## 📄 License

This project is for educational purposes. Add your license here if needed.

---

## 📬 Contact

For questions or support, contact the project maintainer.
