
# Ứng Dụng Quản Lý Điểm Thi Đại Học

## Mục Đích
Ứng dụng Quản Lý Điểm Thi Đại Học được thiết kế để hỗ trợ các tổ chức giáo dục và trường đại học quản lý thông tin điểm thi của sinh viên một cách hiệu quả.

Ứng dụng được xây dựng bằng ngôn ngữ lập trình Java và sử dụng giao diện đồ họa Swing để tương tác với người dùng. Chương trình này giúp người dùng thêm, sửa, xóa thông tin sinh viên và thực hiện các chức năng như sắp xếp, tìm kiếm, thống kê điểm thi.

## Yêu Cầu Hệ Thống
- Ngôn ngữ lập trình: Java
- Sử dụng Java Swing để xây dựng giao diện người dùng
- Hệ quản trị cơ sở dữ liệu: XML hoặc một hệ quản trị cơ sở dữ liệu khác
- Apache hoặc Nginx để triển khai ứng dụng

## Cài Đặt và Chạy Ứng Dụng
1. Clone mã nguồn từ kho lưu trữ GitHub: https://github.com/ka31504/QuanLyDiemDH
2. Mở project trong môi trường phát triển Java như Eclipse, JDK hoặc IntelliJ.
3. Cấu hình kết nối cơ sở dữ liệu trong file cấu hình.
4. Chạy ứng dụng từ main class `QuanLyDiemDH`.

## Các Chức Năng Chính

1. **Thêm Sinh Viên**: Cho phép người dùng nhập thông tin mới về sinh viên, bao gồm ID, tên, tuổi, địa chỉ, khối, và điểm thi từng môn.

2. **Sửa Sinh Viên**: Người dùng có thể cập nhật thông tin của sinh viên đã tồn tại trong danh sách.

3. **Xóa Sinh Viên**: Cho phép người dùng xóa thông tin của sinh viên khỏi danh sách.

4. **Sắp Xếp Danh Sách**: Cung cấp hai chức năng sắp xếp danh sách sinh viên theo ID và theo tên.

5. **Tìm Kiếm Sinh Viên**: Người dùng có thể tìm kiếm sinh viên theo tên với khả năng chấp nhận một số lượng nhất định các ký tự sai khác.

6. **Thống Kê Điểm Thi**: Hiển thị thông tin thống kê tổng số sinh viên, điểm trung bình, điểm cao nhất và điểm thấp nhất trong danh sách.

## Cách Sử Dụng
1. **Đăng nhập vào hệ thống**:
   - Nhập UserName và Password
   - Sau khi nhập xong click Login
2. **Thêm Sinh Viên**:
   - Nhập thông tin sinh viên vào các trường.
   - Nhấn nút "Thêm" để thêm sinh viên mới.
   - Nhấn nút "Clear" để dọn sạch thông tin trên bảng nhập.

3. **Sửa Sinh Viên**:
   - Chọn một sinh viên từ danh sách.
   - Cập nhật thông tin cần thiết.
   - Nhấn nút "Sửa" để lưu thay đổi.

4. **Xóa Sinh Viên**:
   - Chọn một sinh viên từ danh sách.
   - Nhấn nút "Xóa" để xóa sinh viên.

5. **Sắp Xếp Danh Sách**:
   - Nhấn nút "Sắp xếp theo ID" để sắp xếp theo ID.
   - Nhấn nút "Sắp xếp theo Tên" để sắp xếp theo tên.

6. **Tìm Kiếm Sinh Viên**:
   - Nhấn nút "Tìm Kiếm", cửa sổ tìm kiếm sẽ hiển thị.
   - Nhập tên sinh viên cần tìm kiếm và nhấn Search.
   - Thông tin sinh viên được hiển thị theo tìm kiếm gần đúng.

7. **Thống Kê Điểm Thi**:
   - Nhấn nút "Thống Kê".
   - Hiển thị cửa sổ thông báo với tổng số sinh viên, điểm trung bình, điểm cao nhất và điểm thấp nhất.

## Hình Ảnh Minh Họa

![Chương trình quản lý điểm thi đại học](https://scontent.fhan15-1.fna.fbcdn.net/v/t39.30808-6/421070756_891692016073571_4267684315351951944_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=3635dc&_nc_ohc=Adq9YVPo5fgAX9DAc-2&_nc_ht=scontent.fhan15-1.fna&oh=00_AfC6EFxo3k6fOmEJYdZinS-VlArlx-vbJd0wZwvBGvMTYw&oe=65B29738)


## Phát Triển Tiếp Theo

- Cải thiện giao diện người dùng và trải nghiệm người dùng.
- Lưu trữ dữ liệu sinh viên vào cơ sở dữ liệu thay vì trong bộ nhớ.
- Thêm tính năng xuất file và nhập file để lưu và tải danh sách sinh viên.


## Hỗ Trợ và Phản Hồi
Tên tác giả: Phạm Quang Khải, Trịnh Phúc Lương, Nguyễn Thị Phương Anh.

Nếu bạn gặp vấn đề hoặc có ý kiến đóng góp, vui lòng liên hệ với chúng tôi qua email 22010028@st.phenikaa-uni.edu.vn hoặc tạo issue trong kho lưu trữ trên GitHub qua đường link: https://github.com/ka31504/QuanLyDiemDH.git

Chúng tôi mong muốn nhận được phản hồi từ người sử dụng để cải thiện và phát triển ứng dụng ngày càng tốt hơn.

#### Cảm ơn bạn đã sử dụng ứng dụng Quản Lý Điểm Thi Đại Học của chúng tôi!