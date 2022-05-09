# Pré-requisitos
- Um Apache Kafka rodando no seu localhost porta 9092.
- Um tópico criado com nome "ses-topic".
- Um email registrado no SES da AWS.
- Um cliente "standard" AWS com credenciais configurandas na máquina.

# Iniciando projeto

### 1: Abra o projeto em uma IDE (recomendo Intellij), faça o build do projeto para importar as dependências.

### 2: Entre na classe SES dentro do pacote AWS e coloque o email registrado no serviço SES na sua conta AWS e também um email para qual será enviado a mensagem, insira a região em que o email AWS está registrado.

### 3: Inicie o método main da classe "consumer" no pacote kafka.

### 4: Execute o método main na classe "producer" no pacote kafka.
