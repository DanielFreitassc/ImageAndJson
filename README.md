# Documentação de Uso da Aplicação Backend com MinIO

## Executando a Aplicação
Para rodar a aplicação backend, é necessário executar o seguinte comando dentro do diretório `backend`:
```bash
docker-compose up -d
```

### **Atenção**
Antes de rodar o comando acima, é necessário modificar alguns parâmetros no arquivo `docker-compose.yml`.

Na seção `volumes`, existe um caminho que precisa ser alterado para o diretório correto no seu sistema.
Exemplo da configuração no arquivo:
```yaml
volumes:
  - /home/dan/Imagens/data
```

Você deve criar um diretório no seu sistema e pegar o caminho até ele. Aqui está como encontrar o caminho correto:

- **Linux**: Navegue até o diretório desejado e execute:
  ```bash
  pwd
  ```
- **Windows**: Use o Explorer para navegar até o diretório desejado, segure **Shift**, clique com o botão direito e escolha "Copiar como caminho".
  - No arquivo `docker-compose.yml`, lembre-se de substituir `\` por `/` e remover aspas duplas.

---

## **Acessando o MinIO**
Para acessar o painel administrativo do MinIO, abra o seguinte endereço no navegador:
```
http://localhost:9001
```
Use as credenciais padrões:
- **Usuário**: `ROOTUSER`
- **Senha**: `CHANGEME123`

No painel, crie um **bucket** chamado `images`.

---

## **Endpoints da API**

### **Salvar uma imagem**
Para salvar uma imagem, faça uma requisição `POST` para:
```
http://localhost:8080/images
```

**Parâmetros (form-data):**
- `multipartFile` - A foto que deseja salvar
- `name` - Nome da foto
- `description` - Descrição da foto

---

### **Listar todas as imagens**
Para listar todas as imagens salvas:
```
GET http://localhost:8080/images
```

### **Buscar uma imagem por ID**
```
GET http://localhost:8080/images/{id}
```

### **Visualizar uma imagem diretamente no MinIO**
Se o bucket estiver público, você pode acessar a imagem diretamente via:
```
http://localhost:9000/images/{OBJECT_ID}
```

### **Visualizar uma imagem através do backend**
```
GET http://localhost:8080/images/{OBJECT_ID}/photo
```

### **Atualizar uma imagem**
```
PUT http://localhost:8080/images
```

**Parâmetros (form-data):**
- `multipartFile` - A nova foto (opcional)
- `name` - Novo nome (opcional)
- `description` - Nova descrição (opcional)

### **Deletar uma imagem**
Para deletar uma imagem, faça uma requisição `DELETE` para:
```
DELETE http://localhost:8080/images/{id}
```
