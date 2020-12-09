# -- Dockerfile --
# 这个文件负责构建包含你的程序的 Docker 容器

# 使用 Java 8
FROM openjdk:8-alpine
# 向容器内复制文件
COPY ./* /app/
# 编译程序
WORKDIR /app/
RUN javac *.java
RUN javac ./analyser/*.java
RUN javac ./error/*.java
RUN javac ./instruction/*.java
RUN javac ./tokenizer/*.java
RUN javac ./analyser/expr/*.java
RUN javac ./analyser/function/*.java
RUN javac ./analyser/program/*.java
RUN javac ./analyser/statement/*.java
RUN javac ./analyser/symbol/*.java
RUN javac ./analyser/util/*.java