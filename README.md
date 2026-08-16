# LagFix Mod (Fabric - Minecraft 1.21.1)

## Build bằng điện thoại (không cần máy tính)
Project này đã có sẵn file `.github/workflows/build.yml` để GitHub tự
build giúp bạn. Xem hướng dẫn chi tiết ở cuối file README.

## Cách mở project (nếu dùng máy tính)
1. Cài JDK 21 và IntelliJ IDEA.
2. Mở thư mục này bằng IntelliJ (File > Open).
3. Chạy `./gradlew genSources` trong terminal của IntelliJ để tải mapping.
4. Đợi Gradle sync xong.

## Chạy thử
- `./gradlew runClient` để chạy client test có mod.
- `./gradlew runServer` để chạy server test.

## Build ra file .jar để cài vào server thật
- `./gradlew build`
- File jar sẽ nằm ở `build/libs/lagfix-1.0.0.jar`
- Bỏ file này vào thư mục `mods` của server Fabric.

## Cách hoạt động
Mod chèn logic vào `mobTick()` của mọi mob. Nếu mob ở xa người chơi
(xem LagFixConfig.java), mod sẽ bỏ qua phần lớn các tick AI của nó,
chỉ cho chạy lại theo chu kỳ. Mob đang đuổi theo người chơi (có target)
luôn được tick đầy đủ để tránh đứng hình bất thường.

## Nếu bạn muốn build cho bản 26.2 thay vì 1.21.1
1. Sửa `gradle.properties`: đổi `minecraft_version`, `yarn_mappings`,
   `loader_version`, `fabric_version` sang bản tương ứng cho 26.2
   (xem trên trang Fabric/Modrinth để lấy số bản chính xác mới nhất).
2. Chạy lại `./gradlew genSources` và kiểm tra trong
   MobEntityMixin.java xem tên phương thức `mobTick` có còn đúng
   không (Minecraft đổi tên mapping giữa các bản khá thường xuyên).

## Build jar bằng điện thoại qua GitHub Actions

1. Giải nén file `lagfix-mod.zip` bằng app quản lý file có sẵn trên điện thoại
   (Android: "Files"/"My Files"; iOS: app "Files").

2. Vào github.com bằng trình duyệt điện thoại, đăng nhập hoặc tạo tài khoản.

3. Bấm dấu "+" góc trên → "New repository". Đặt tên (vd `lagfix-mod`),
   để **Public** hoặc **Private** tùy bạn, KHÔNG tích "Add README"
   (để tránh xung đột), bấm "Create repository".

4. Ở trang repo trống, bấm dòng chữ
   **"uploading an existing file"**.

5. Bấm **"choose your files"**, sau đó chọn TẤT CẢ file/thư mục đã
   giải nén (Chrome trên Android cho chọn cả thư mục; trên iOS Safari
   nếu không chọn được thư mục thì chọn từng file — kéo/thả nhiều lần
   theo từng thư mục con: `src`, `.github`, rồi các file gốc).
   Quan trọng: giữ đúng cấu trúc thư mục, không được để lẫn lộn file.

6. Bấm "Commit changes" ở cuối trang để upload.

7. Vào tab **"Actions"** ở đầu trang repo. Sẽ thấy 1 workflow đang chạy
   (hoặc bấm "Run workflow" nếu chưa tự chạy). Đợi vài phút tới khi
   thấy dấu tích xanh ✅.

8. Bấm vào lượt chạy đó → kéo xuống mục **"Artifacts"** → tải file
   `lagfix-jar` về điện thoại. Đây chính là file `.jar` để bỏ vào
   thư mục `mods` của server.

Nếu bước 5 upload thiếu file hoặc sai cấu trúc, bạn có thể sửa/thêm
file trực tiếp trên GitHub (bấm "Add file" → "Create new file") rồi
gõ lại đường dẫn đầy đủ, ví dụ:
`src/main/java/com/example/lagfix/LagFixMod.java`
