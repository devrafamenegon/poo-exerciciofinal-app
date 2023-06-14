# Aplicativo de Login e Estoque de Produtos :key::package:

Este é um aplicativo de exemplo que demonstra um sistema de login e gerenciamento de estoque de produtos. O objetivo é ilustrar o uso da arquitetura Model-View-Presenter (MVP) no desenvolvimento de aplicativos Android nativos utilizando a linguagem Java. :iphone:

## Funcionalidades :gear::

O aplicativo possui as seguintes funcionalidades:

- Autenticação de usuário através de login e senha. :closed_lock_with_key:
- Consulta e listagem de produtos disponíveis no estoque. :clipboard:

## Backend :computer::

O aplicativo consome dados de um backend remoto, que pode ser encontrado no repositório GitHub: [Backend do Aplicativo](https://github.com/devrafamenegon/poo-exerciciofinal-backend). Certifique-se de configurar e executar o backend antes de executar o aplicativo.

## Pré-requisitos :heavy_check_mark::

Antes de executar o aplicativo, você precisa ter o seguinte configurado:

- Android Studio (versão 2022.1.1 ou superior) :computer:
- JDK (versão 11.0.19 ou superior) :coffee:
- Dispositivo Android ou Emulador :iphone:

## Configuração :wrench::

1. Clone este repositório em sua máquina.
2. Abra o projeto no Android Studio.
3. Execute o aplicativo em seu dispositivo Android ou emulador.

## Arquitetura MVP :triangular_ruler::

O aplicativo utiliza a arquitetura Model-View-Presenter (MVP), que é uma abordagem comum no desenvolvimento de aplicativos Android. A arquitetura MVP separa as responsabilidades em três componentes principais:

- **Model**: Responsável por representar os dados e as regras de negócio do aplicativo. Neste projeto, a pasta `Model` contém as classes relacionadas ao domínio do aplicativo, como `Conta` e `Product`.

- **View**: Responsável por exibir os elementos de interface do usuário (UI) e interagir com o usuário. Neste projeto, a pasta `View` contém as atividades do Android, como `LoginActivity` e `EstoqueActivity`, que implementam as interfaces definidas nos contratos.

- **Presenter**: Atua como intermediário entre o Model e a View. É responsável por receber as ações do usuário na View, processá-las e atualizar a UI com base nas alterações do Model. Neste projeto, a pasta `Presenter` contém as implementações dos presenters, como `LoginPresenter` e `EstoquePresenter`, que implementam as interfaces definidas nos contratos.

- **Contract**: Define os contratos (interfaces) entre a View e o Presenter. Neste projeto, a pasta `Contract` contém as interfaces `LoginContract` e `EstoqueContract`, que especificam os métodos que devem ser implementados pela View e pelo Presenter.

A arquitetura MVP visa separar as preocupações, facilitando a manutenção, testabilidade e escalabilidade do aplicativo. Ela promove uma separação clara entre as responsabilidades dos componentes, permitindo que sejam modificados independentemente um do outro.

## Código-fonte :floppy_disk::

Você pode encontrar o código-fonte completo do aplicativo neste repositório. Sinta-se à vontade para explorar, fazer melhorias e aprender com ele.

Se tiver mais alguma dúvida, estou aqui para ajudar! :smiley: