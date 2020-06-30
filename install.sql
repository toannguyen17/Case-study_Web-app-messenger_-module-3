/*
 * Thiết lập mặc định
 */
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- --------------------------------------------------------
/*
 * Tạo bảng
 */
-- --------------------------------------------------------

/*
 * Tạo bảng users
 */
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `password`       VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at`     TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*
 * Tạo bảng user_infos
 */
DROP TABLE IF EXISTS `user_infos`;
CREATE TABLE `user_infos` (
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`          BIGINT(20) UNSIGNED NOT NULL UNIQUE,
    `last_name`        VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `first_name`       VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `avatar_extension` VARCHAR(6) COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
    `updated_at`       TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng phones
 */
DROP TABLE IF EXISTS `phones`;
CREATE TABLE `phones` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`    BIGINT(20) UNSIGNED NOT NULL,
    `region_id`  BIGINT(20) UNSIGNED NOT NULL,
    `number`     VARCHAR(12) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
    `created_at` TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng phone_regions (Ma vung)
 */
DROP TABLE IF EXISTS `phone_regions`;
CREATE TABLE `phone_regions` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `region`     VARCHAR(6) COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE,
    `created_at` TIMESTAMP NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng contacts
 */
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
    `id`         BIGINT(20)  UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `type`       TINYINT(12) UNSIGNED NOT NULL DEFAULT 0,
    `created_at` TIMESTAMP   NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng my_contacts
 */
DROP TABLE IF EXISTS `my_contacts`;
CREATE TABLE `my_contacts` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`    BIGINT(20) UNSIGNED NOT NULL,
    `contact_id` BIGINT(20) UNSIGNED NOT NULL,
    `delete`     TINYINT(1) UNSIGNED NULL DEFAULT 0,
    `created_at` TIMESTAMP  NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng messages
 */
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `contact_id` BIGINT(20) UNSIGNED NOT NULL,
    `user_id`    BIGINT(20) UNSIGNED NOT NULL,
    `delete`     TINYINT(2) UNSIGNED NULL DEFAULT 0,
    `created_at` TIMESTAMP  NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng message_texts
 */
DROP TABLE IF EXISTS `message_texts`;
CREATE TABLE `message_texts` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `message_id` BIGINT(20) UNSIGNED NOT NULL,
    `text`       TEXT COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng message_stickers
 */
DROP TABLE IF EXISTS `message_stickers`;
CREATE TABLE `message_stickers` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `message_id` BIGINT(20) UNSIGNED NOT NULL,
    `sticker_id` BIGINT(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng stickers
 */
DROP TABLE IF EXISTS `stickers`;
CREATE TABLE `stickers` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `group_id`   BIGINT(20) UNSIGNED NOT NULL,
    `sticker`    VARCHAR(500) COLLATE utf8mb4_unicode_ci NOT NULL,
    `created_at` TIMESTAMP  NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
 * Tạo bảng group_stickers
 */
DROP TABLE IF EXISTS `group_stickers`;
CREATE TABLE `group_stickers` (
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`       VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `desc`       VARCHAR(500) COLLATE utf8mb4_unicode_ci NOT NULL,
    `created_at` TIMESTAMP  NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


 /*
 * Thiết lập ràng buộc
 */
--
-- Các ràng buộc cho bảng `user_infos`
--
ALTER TABLE `user_infos`
  ADD CONSTRAINT `user_infos_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `phones`
--
ALTER TABLE `phones`
  ADD CONSTRAINT `phones_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `phones_region_id_foreign` FOREIGN KEY (`region_id`) REFERENCES `phone_regions` (`id`);


--
-- Các ràng buộc cho bảng `my_contacts`
--
ALTER TABLE `my_contacts`
  ADD CONSTRAINT `my_contacts_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `my_contacts_region_id_foreign` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`id`);



--
-- Các ràng buộc cho bảng `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_contact_id_foreign` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`id`);


--
-- Các ràng buộc cho bảng `message_texts`
--
ALTER TABLE `message_texts`
  ADD CONSTRAINT `message_text_messag_id_foreign` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`);


--
-- Các ràng buộc cho bảng `message_stickers`
--
ALTER TABLE `message_stickers`
  ADD CONSTRAINT `message_sticker_messag_id_foreign` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`),
  ADD CONSTRAINT `message_sticker_sticker_id_foreign` FOREIGN KEY (`sticker_id`) REFERENCES `stickers` (`id`);



--
-- Các ràng buộc cho bảng `message_texts`
--
ALTER TABLE `stickers`
  ADD CONSTRAINT `stickers_group_id_foreign` FOREIGN KEY (`group_id`) REFERENCES `group_stickers` (`id`);

