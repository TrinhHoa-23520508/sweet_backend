-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: sweet
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `chi_tiet_quy_dinh_lai_suat`
--

LOCK TABLES `chi_tiet_quy_dinh_lai_suat` WRITE;
/*!40000 ALTER TABLE `chi_tiet_quy_dinh_lai_suat` DISABLE KEYS */;
INSERT INTO `chi_tiet_quy_dinh_lai_suat` VALUES (1,0.47,1,3,1,3),(2,0.44,3,3,1,3),(3,0.57,6,3,1,3),(4,0.6,12,3,1,3),(5,0.428,1,3,1,4),(6,0.443,3,3,1,4),(7,0.568,6,3,1,4),(8,0.557,12,3,1,4),(9,0.448,3,3,1,1),(10,0.581,6,3,1,1),(11,0.581,12,3,1,1),(12,0.57,18,3,1,1),(13,0.585,6,3,1,2),(14,0.584,12,3,1,2),(15,0.573,18,3,1,2),(16,0.592,24,3,1,2),(17,0.47,1,4,1,NULL),(18,0.44,3,4,1,NULL),(19,0.57,6,4,1,NULL),(20,0.6,12,4,1,NULL);
/*!40000 ALTER TABLE `chi_tiet_quy_dinh_lai_suat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dia_chi`
--

LOCK TABLES `dia_chi` WRITE;
/*!40000 ALTER TABLE `dia_chi` DISABLE KEYS */;
INSERT INTO `dia_chi` VALUES (1,'foo','foo','1','foo','foo'),(2,'Phường 7','Quận 5','456','Nguyễn Trãi','TP. Hồ Chí Minh'),(3,'Phường 5','Quận 3','123','Lê Lợi','TP. Hồ Chí Minh'),(4,'Phường 7','Quận 5','456','Nguyễn Trãi','TP. Hồ Chí Minh'),(5,'Phường 5','Quận 3','123','Lê Lợi','TP. Hồ Chí Minh'),(6,'Phường 5','Quận 3','123','Lê Lợi','TP. Hồ Chí Minh'),(7,'Phường 7','Quận 5','456','Nguyễn Trãi','TP. Hồ Chí Minh'),(8,'Phường 5','Quận 3','123','Lê Lợi','TP. Hồ Chí Minh'),(9,'Phường 7','Quận 5','456','Nguyễn Trãi','TP. Hồ Chí Minh'),(10,'Phường 5','Quận 3','123','Lê Lợi','TP. Hồ Chí Minh'),(11,'Phường 7','Quận 5','456','Nguyễn Trãi','TP. Hồ Chí Minh');
/*!40000 ALTER TABLE `dia_chi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `giao_dich`
--

LOCK TABLES `giao_dich` WRITE;
/*!40000 ALTER TABLE `giao_dich` DISABLE KEYS */;
/*!40000 ALTER TABLE `giao_dich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hinh_thuc_dao_han`
--

LOCK TABLES `hinh_thuc_dao_han` WRITE;
/*!40000 ALTER TABLE `hinh_thuc_dao_han` DISABLE KEYS */;
INSERT INTO `hinh_thuc_dao_han` VALUES (1,1,'Nhận toàn bộ tiền gốc và lãi khi đáo hạn','Tất toán phiếu gửi tiền'),(2,2,'Nhận lãi và tự động gửi lại tiền gốc','Tái tục gốc'),(3,3,'Tự động gửi lại cả gốc và lãi khi đáo hạn','Tự động tái tục gốc và lãi');
/*!40000 ALTER TABLE `hinh_thuc_dao_han` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `kenh_giao_dich`
--

LOCK TABLES `kenh_giao_dich` WRITE;
/*!40000 ALTER TABLE `kenh_giao_dich` DISABLE KEYS */;
INSERT INTO `kenh_giao_dich` VALUES (1,1,'Giao dịch tại quầy','Giao dịch tại quầy'),(2,2,'Giao dịch trực tuyến','Giao dịch trực tuyến');
/*!40000 ALTER TABLE `kenh_giao_dich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES (1,'12345678245','khachhang@gmail.com','Nguyễn Văn A','$2a$10$wOZ/NVlD9W7CiQtAdQwVbOM7ioDDi5DyIZywo0XPUtoKGh00FgYly','2025-06-21','1995-08-15','0912345678',29,7,6,1,11,2),(2,'12345678255','khachhangguitien@gmail.com','khách gửi tiên','$2a$10$qI5SHkMZorH2IHWhzsl5C.8OlJirqt5FnzJ.93/SuV3DW4F9Ef4J6','2025-06-21','1995-08-15','0912345678',29,9,8,1,11,4),(3,'12345678265','khachhangthanhtoan@gmail.com','khách thanh toán','$2a$10$RapvXa5zl.YUpnuTLn2EZeN9/M9ZSUBg3LA7L0nmFy129MBdFJxz.','2025-06-21','1995-08-15','0912345678',29,11,10,1,11,3);
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lich_su_giao_dich_tktt`
--

LOCK TABLES `lich_su_giao_dich_tktt` WRITE;
/*!40000 ALTER TABLE `lich_su_giao_dich_tktt` DISABLE KEYS */;
/*!40000 ALTER TABLE `lich_su_giao_dich_tktt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lichsugiaodich_phieuguitien`
--

LOCK TABLES `lichsugiaodich_phieuguitien` WRITE;
/*!40000 ALTER TABLE `lichsugiaodich_phieuguitien` DISABLE KEYS */;
/*!40000 ALTER TABLE `lichsugiaodich_phieuguitien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_giao_dich`
--

LOCK TABLES `loai_giao_dich` WRITE;
/*!40000 ALTER TABLE `loai_giao_dich` DISABLE KEYS */;
INSERT INTO `loai_giao_dich` VALUES (1,1,'Tài khoản thanh toán','Tài khoản thanh toán'),(2,2,'Phiếu gửi tiền','Phiếu gửi tiền'),(3,3,'Tiền mặt tại quầy','Tiền mặt tại quầy'),(4,4,'Ngân hàng','Ngân hàng');
/*!40000 ALTER TABLE `loai_giao_dich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_ky_han`
--

LOCK TABLES `loai_ky_han` WRITE;
/*!40000 ALTER TABLE `loai_ky_han` DISABLE KEYS */;
INSERT INTO `loai_ky_han` VALUES (1,1,'1 Tháng'),(2,2,'2 Tháng'),(3,3,'3 Tháng'),(4,4,'4 Tháng'),(5,5,'5 Tháng'),(6,6,'6 Tháng'),(7,7,'7 Tháng'),(8,8,'8 Tháng'),(9,9,'9 Tháng'),(10,10,'10 Tháng'),(11,11,'11 Tháng'),(12,12,'12 Tháng'),(13,13,'13 Tháng'),(14,14,'14 Tháng'),(15,15,'15 Tháng'),(16,16,'16 Tháng'),(17,17,'17 Tháng'),(18,18,'18 Tháng'),(19,19,'19 Tháng'),(20,20,'20 Tháng'),(21,21,'21 Tháng'),(22,22,'22 Tháng'),(23,23,'23 Tháng'),(24,24,'24 Tháng');
/*!40000 ALTER TABLE `loai_ky_han` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_tai_khoan`
--

LOCK TABLES `loai_tai_khoan` WRITE;
/*!40000 ALTER TABLE `loai_tai_khoan` DISABLE KEYS */;
INSERT INTO `loai_tai_khoan` VALUES (1,0,'Tài khoản thanh toán','Tài khoản thanh toán'),(2,1,'Phiếu gửi tiền','Phiếu gửi tiền'),(3,2,'Tiền mặt tại quầy','Tiền mặt tại quầy'),(4,3,'Ngân hàng','Ngân hàng');
/*!40000 ALTER TABLE `loai_tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_tiet_kiem`
--

LOCK TABLES `loai_tiet_kiem` WRITE;
/*!40000 ALTER TABLE `loai_tiet_kiem` DISABLE KEYS */;
INSERT INTO `loai_tiet_kiem` VALUES
(1,_binary '',_binary '\0',_binary '\0',1,'Phải gửi đủ thời gian mới được hưởng lãi suất tối đa','Tiết kiệm có kỳ hạn'),
(2,_binary '',_binary '',_binary '',2,'Cho phép rút một phần tiền trước hạn','Tiết kiệm linh hoạt');
/*!40000 ALTER TABLE `loai_tiet_kiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_trang_thai`
--

LOCK TABLES `loai_trang_thai` WRITE;
/*!40000 ALTER TABLE `loai_trang_thai` DISABLE KEYS */;
INSERT INTO `loai_trang_thai` VALUES (1,_binary '\0','customer','Khách hàng','Khách hàng'),(2,_binary '\0','employee','Nhân viên','Nhân viên'),(3,_binary '\0','payment_account','Tài khoản thanh toán','Tài khoản thanh toán'),(4,_binary '\0','transaction','Giao dịch','Giao dịch'),(5,_binary '\0','deposit_receipt','Phiếu gửi tiền','Phiếu gửi tiền'),(6,_binary '\0','login_account','Tài khoản đăng nhập','Tài khoản đăng nhập');
/*!40000 ALTER TABLE `loai_trang_thai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
INSERT INTO `nhan_vien` VALUES
(1,'12345678','foo@bar.com','123','123456789','2025-06-21','2025-06-21','1234567',16,1,1,3,1),
(2,'12345678245','admin@gmail.com','admin','$2a$10$TpXko42NquLQjkmYPz7oee7tLV.evO0ZFQNkqmO669x.kXvrqpVey','1995-08-15','2025-06-21','0912345678',29,4,5,11,5);
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phieu_dao_han`
--

LOCK TABLES `phieu_dao_han` WRITE;
/*!40000 ALTER TABLE `phieu_dao_han` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieu_dao_han` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phieu_gui_tien`
--

LOCK TABLES `phieu_gui_tien` WRITE;
/*!40000 ALTER TABLE `phieu_gui_tien` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieu_gui_tien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phieu_rut_tien`
--

LOCK TABLES `phieu_rut_tien` WRITE;
/*!40000 ALTER TABLE `phieu_rut_tien` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieu_rut_tien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phieu_tra_lai`
--

LOCK TABLES `phieu_tra_lai` WRITE;
/*!40000 ALTER TABLE `phieu_tra_lai` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieu_tra_lai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quy_dinh_lai_suat`
--

LOCK TABLES `quy_dinh_lai_suat` WRITE;
/*!40000 ALTER TABLE `quy_dinh_lai_suat` DISABLE KEYS */;
INSERT INTO `quy_dinh_lai_suat` VALUES (1,0.1,'Blabla','2025-06-21','2025-06-21',2147483647,1);
/*!40000 ALTER TABLE `quy_dinh_lai_suat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quyen_han`
--

LOCK TABLES `quyen_han` WRITE;
/*!40000 ALTER TABLE `quyen_han` DISABLE KEYS */;
INSERT INTO `quyen_han` VALUES
(1,'/api/v1/phieu-tra-lai','GET','PhieuTraLai','Lấy tất cả phiếu trả lãi'),
(2,'/api/v1/phieu-tra-lai','POST','PhieuTraLai','Tạo mới phiếu trả lãi'),
(3,'/api/v1/phieu-gui-tien','GET','PhieuGuiTien','Lấy tất cả phiếu gửi tiền'),
(4,'/api/v1/phieu-gui-tien','POST','PhieuGuiTien','Tạo mới phiếu gửi tiền'),
(5,'/api/v1/phieu-gui-tien/{khachHangId}','GET','PhieuGuiTien','Lấy phiếu gửi tiền theo khách hàng'),
(6,'/api/v1/phieu-gui-tien/{id}/phieu-tra-lai','GET','PhieuTraLai','Lấy phiếu trả lãi theo phiếu gửi tiền'),
(7,'/api/v1/giao-dich','GET','GiaoDich','Lấy tất cả giao dịch'),
(8,'/api/v1/giao-dich/{id}','GET','GiaoDich','Lấy giao dịch theo mã giao dịch'),
(9,'/api/v1/giao-dich','POST','GiaoDich','Tạo giao dịch'),
(10,'/api/v1/giao-dich/tktt','GET','TaiKhoanThanhToan','Lấy tất cả tài khoản thanh toán'),
(11,'/api/v1/giao-dich/tktt/{id}','GET','TaiKhoanThanhToan','Lấy tài khoản thanh toán theo mã tài khoản'),
(12,'/api/v1/giao-dich/tktt','POST','TaiKhoanThanhToan','Tạo tài khoản thanh toán'),
(13,'/api/v1/giao-dich/tktt','GET','LichSuGiaoDich_TKTT','Lấy tất cả lịch sử giao dịch tài khoản thanh toán'),
(14,'/api/v1/giao-dich/tktt/{id}','GET','LichSuGiaoDich_TKTT','Lấy tất cả lịch sử giao dịch tài khoản thanh toán theo mã lịch sử giao dịch'),
(15,'/api/v1/quy-dinh-lai-suat','GET','QuyDinhLaiSuat','Lấy tất cả quy định lãi suất'),
(16,'/api/v1/quy-dinh-lai-suat/{id}','GET','QuyDinhLaiSuat','Lấy quy định lãi suất theo mã quy định lãi suất'),
(17,'/api/v1/quy-dinh-lai-suat/chi-tiet','GET','ChiTietQuyDinhLaiSuat','Lấy tất cả chi tiết quy định lãi suất'),
(18,'/api/v1/quy-dinh-lai-suat/chi-tiet/{id}','GET','ChiTietQuyDinhLaiSuat','Lấy tất cả chi tiết quy định lãi suất theo mã chi tiết quy định lãi suất'),
(19,'/api/v1/dia-chi','POST','DiaChi','Tạo địa chỉ mới'),
(20,'/api/v1/dia-chi/{id}','GET','DiaChi','Lấy địa chỉ theo ID'),
(21,'/api/v1/dia-chi/{id}','PUT','DiaChi','Cập nhật địa chỉ theo ID'),
(22,'/api/v1/dia-chi','GET','DiaChi','Lấy danh sách địa chỉ có filter'),
(23,'/api/v1/khach-hang','POST','KhachHang','Tạo khách hàng mới'),
(24,'/api/v1/khach-hang/{id}','GET','KhachHang','Lấy khách hàng theo ID'),
(25,'/api/v1/khach-hang','GET','KhachHang','Lấy danh sách khách hàng có filter'),
(26,'/api/v1/khach-hang/{id}','PUT','KhachHang','Cập nhật thông tin khách hàng'),
(27,'/api/v1/khach-hang/{id}/vo-hieu-hoa','PUT','KhachHang','Vô hiệu hóa tài khoản khách hàng'),
(28,'/api/v1/khach-hang/{id}/kich-hoat','PUT','KhachHang','Kích hoạt tài khoản khách hàng'),
(29,'/api/v1/loai-trang-thai','POST','LoaiTrangThai','Tạo loại trạng thái mới'),(30,'/api/v1/loai-trang-thai','GET','LoaiTrangThai','Lấy danh sách loại trạng thái có filter'),(31,'/api/v1/loai-trang-thai/{id}','GET','LoaiTrangThai','Lấy loại trạng thái theo ID'),(32,'/api/v1/loai-trang-thai/{id}','PUT','LoaiTrangThai','Cập nhật loại trạng thái'),(33,'/api/v1/loai-trang-thai/{id}','DELETE','LoaiTrangThai','Xóa loại trạng thái'),(34,'/api/v1/nhan-vien','POST','NhanVien','Tạo nhân viên mới'),(35,'/api/v1/nhan-vien/{id}','GET','NhanVien','Lấy nhân viên theo ID'),(36,'/api/v1/nhan-vien','GET','NhanVien','Lấy danh sách nhân viên có filter'),(37,'/api/v1/nhan-vien/{id}','PUT','NhanVien','Cập nhật thông tin nhân viên'),(38,'/api/v1/nhan-vien/{id}/vo-hieu-hoa','PUT','NhanVien','Vô hiệu hóa tài khoản nhân viên'),(39,'/api/v1/nhan-vien/{id}/kich-hoat','PUT','NhanVien','Kích hoạt tài khoản nhân viên'),(40,'/api/v1/quyen-han','POST','QuyenHan','Tạo quyền hạn mới'),(41,'/api/v1/quyen-han','GET','QuyenHan','Lấy danh sách quyền hạn có filter'),(42,'/api/v1/quyen-han/{id}','GET','QuyenHan','Lấy quyền hạn theo ID'),(43,'/api/v1/quyen-han/{id}','PUT','QuyenHan','Cập nhật quyền hạn theo ID'),(44,'/api/v1/quyen-han/{id}','DELETE','QuyenHan','Xóa quyền hạn theo ID'),(45,'/api/v1/tham-so','POST','ThamSo','Tạo tham số mới'),(46,'/api/v1/tham-so','GET','ThamSo','Lấy danh sách tham số có filter'),(47,'/api/v1/tham-so/{id}','GET','ThamSo','Lấy tham số theo ID'),(48,'/api/v1/tham-so/{id}','PUT','ThamSo','Cập nhật tham số theo ID'),(49,'/api/v1/tham-so/{id}','DELETE','ThamSo','Xóa tham số theo ID'),(50,'/api/v1/trang-thai','POST','TrangThai','Tạo trạng thái mới'),(51,'/api/v1/trang-thai/loai/{loaiTrangThaiId}','GET','TrangThai','Lấy tất cả trạng thái theo loại trạng thái'),(52,'/api/v1/trang-thai/{id}','GET','TrangThai','Lấy trạng thái theo ID'),(53,'/api/v1/trang-thai','GET','TrangThai','Lấy tất cả trạng thái có filter'),(54,'/api/v1/trang-thai/{id}','PUT','TrangThai','Cập nhật trạng thái theo ID'),(55,'/api/v1/trang-thai/{id}','DELETE','TrangThai','Xóa trạng thái theo ID'),(56,'/api/v1/vai-tro','POST','VaiTro','Tạo vai trò mới'),(57,'/api/v1/vai-tro','GET','VaiTro','Lấy danh sách vai trò có filter'),(58,'/api/v1/vai-tro/{id}','GET','VaiTro','Lấy vai trò theo ID'),(59,'/api/v1/vai-tro/{id}','PUT','VaiTro','Cập nhật vai trò theo ID'),(60,'/api/v1/vai-tro/{id}','DELETE','VaiTro','Xóa vai trò theo ID'),(61,'/api/v1/vai-tro/{id}/cap-quyen-module','PUT','VaiTro','Cấp quyền toàn bộ module cho vai trò'),(62,'/api/v1/quyen-han/danh-sach','POST','QuyenHan','tạo danh sách quyền hạn'),(63,'/api/v1/phieu-rut-tien','GET','PhieuRutTien','Lấy danh sách các phiếu rút tiền'),(64,'/api/v1/phieu-rut-tien','POST','PhieuRutTien','Tạo mới một phiếu rút tiền'),(65,'/api/v1/phieu-dao-han','GET','PhieuDaoHan','Lấy các phiếu đáo hạn'),(66,'/api/v1/phieu-dao-han','POST','PhieuDaoHan','Tạo mới một phiếu đáo hạn'),(67,'/api/v1/vai-tro/{id}/xoa-quyen-module','PUT','VaiTro','xóa danh sách quyền hạn của module trong vai trò');
/*!40000 ALTER TABLE `quyen_han` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tai_khoan_thanh_toan`
--

LOCK TABLES `tai_khoan_thanh_toan` WRITE;
/*!40000 ALTER TABLE `tai_khoan_thanh_toan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tai_khoan_thanh_toan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tan_suat_nhan_lai`
--

LOCK TABLES `tan_suat_nhan_lai` WRITE;
/*!40000 ALTER TABLE `tan_suat_nhan_lai` DISABLE KEYS */;
INSERT INTO `tan_suat_nhan_lai` VALUES (1,_binary '',1,'Nhận lãi định kỳ hàng tháng','Hàng tháng'),(2,_binary '',2,'Nhận lãi định kỳ mỗi quý','Hàng quý'),(3,_binary '',3,'Nhận lãi một lần khi đáo hạn','Cuối kỳ hạn'),(4,_binary '',4,'Nhận lãi một lần khi đáo hạn','Đầu kỳ hạn');
/*!40000 ALTER TABLE `tan_suat_nhan_lai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tham_so`
--

LOCK TABLES `tham_so` WRITE;
/*!40000 ALTER TABLE `tham_so` DISABLE KEYS */;
INSERT INTO `tham_so` VALUES (1,'1','MIN_AGE_EMPLOYEE','MIN_AGE_EMPLOYEE','MIN_AGE_EMPLOYEE'),(2,'1','MIN_AGE_CUSTOMER','MIN_AGE_CUSTOMER','MIN_AGE_CUSTOMER'),(3,'1','MIN_DEPOSIT_AMOUNT','MIN_DEPOSIT_AMOUNT','MIN_DEPOSIT_AMOUNT'),(4,'1','NO_TERM_INTEREST_RATE','NO_TERM_INTEREST_RATE','NO_TERM_INTEREST_RATE');
/*!40000 ALTER TABLE `tham_so` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trang_thai`
--

LOCK TABLES `trang_thai` WRITE;
/*!40000 ALTER TABLE `trang_thai` DISABLE KEYS */;
INSERT INTO `trang_thai` VALUES (1,_binary '\0','active','Còn hoạt động',1),(2,_binary '\0','locked','Đã hủy',1),(3,_binary '\0','active','Còn hoạt động',2),(4,_binary '\0','locked','Đã hủy',2),(5,_binary '\0','active','Còn hoạt động',3),(6,_binary '\0','locked','Đã khóa',3),(7,_binary '\0','success','Giao dịch thành công',4),(8,_binary '\0','failed','Giao dịch thất bại',4),(9,_binary '\0','settled','Đã tất toán',5),(10,_binary '\0','unsettled','Chưa tất toán',5),(11,_binary '\0','active','Đang hoạt động',6),(12,_binary '\0','locked','Đã khóa',6);
/*!40000 ALTER TABLE `trang_thai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vai_tro`
--

LOCK TABLES `vai_tro` WRITE;
/*!40000 ALTER TABLE `vai_tro` DISABLE KEYS */;
INSERT INTO `vai_tro` VALUES (1,_binary '','fOO','Foo'),(2,_binary '','tài khoản không có quyền thực hiện điều gì','KHONG_QUYEN_KHACH_HANG'),(3,_binary '','Chỉ có quyền thực hiện các giao dịch thanh toán','QUYEN_THANH_TOAN'),(4,_binary '','Có quyền thanh toán và thực hiện các chức năng tiết kiệm','QUYEN_TIET_KIEM'),(5,_binary '','Có toàn quyền trong hệ thống','QUAN_TRI_VIEN'),(6,_binary '','Không có quyền truy cập vào hệ thống','KHONG_QUYEN_NHAN_VIEN'),(7,_binary '','Xử lý các giao dịch của khách hàng','NHAN_VIEN_GIAO_DICH'),(8,_binary '','Quản lý các sản phẩm và báo cáo','NHAN_VIEN_TIEP_THI');
/*!40000 ALTER TABLE `vai_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vaitro_quyenhan`
--

LOCK TABLES `vaitro_quyenhan` WRITE;
/*!40000 ALTER TABLE `vaitro_quyenhan` DISABLE KEYS */;
INSERT INTO `vaitro_quyenhan` VALUES (5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(5,12),(5,13),(5,14),(5,15),(5,16),(5,17),(5,18),(5,19),(5,20),(5,21),(5,22),(5,23),(5,24),(5,25),(5,26),(5,27),(5,28),(5,29),(5,30),(5,31),(5,32),(5,33),(5,34),(5,35),(5,36),(5,37),(5,38),(5,39),(5,40),(5,41),(5,42),(5,43),(5,44),(5,45),(5,46),(5,47),(5,48),(5,49),(5,50),(5,51),(5,52),(5,53),(5,54),(5,55),(5,56),(5,57),(5,58),(5,59),(5,60),(5,61),(5,62),(5,65),(5,66),(5,63),(5,64),(5,67),(3,7),(3,8),(3,9),(3,13),(3,14),(3,10),(3,11),(3,12);
/*!40000 ALTER TABLE `vaitro_quyenhan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-21  3:35:54
