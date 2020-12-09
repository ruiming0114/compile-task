# -- Dockerfile --
# 这个文件负责构建包含你的程序的 Docker 容器

# 使用 Java 8
FROM openjdk:8-alpine
# 向容器内复制文件
COPY ./Coder.java /app/
COPY ./analyser/expr/* /app/analyser/expr/
COPY ./analyser/function/* /app/analyser/function/
COPY ./analyser/program/* /app/analyser/program/
COPY ./analyser/statement/* /app/analyser/statement/
COPY ./analyser/symbol/* /app/analyser/symbol/
COPY ./analyser/util/* /app/analyser/util/
COPY ./analyser/Analyser.java /app/analyser/
COPY ./error/* /app/error/
COPY ./instruction/* /app/instruction/
COPY ./tokenizer/* /app/tokenizer/
# 编译程序
WORKDIR /app/
RUN javac -d . *.java