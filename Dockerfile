# ベースイメージとしてOpenJDK 17のslimバージョンを使用
FROM openjdk:21-jdk-slim

# 作成したアプリケーションのJarファイルをイメージ内にコピー
COPY target/remind##0.0.1-SNAPSHOT.war /dockerremind.war

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "/dockerremind.war"]
