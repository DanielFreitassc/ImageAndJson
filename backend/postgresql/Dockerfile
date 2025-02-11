FROM postgres:latest

# Define as variáveis de ambiente padrão
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_DB=api

# Define o diretório de trabalho dentro do container
WORKDIR /var/lib/postgresql/data

# Expondo a porta padrão do PostgreSQL
EXPOSE 5432

# Comando para iniciar o PostgreSQL
CMD ["postgres"]
