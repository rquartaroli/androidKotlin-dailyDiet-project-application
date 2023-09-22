![cover](.github/cover.png?style=flat)

## 💻 Projeto
Aplicativo para criar e monitorar suas refeições diárias, lhe ajudando a seguir e manter o controle de uma dieta mais elaborada. 

## ✨ Tecnologias

- [Kotlin](https://developer.android.com/kotlin?gclid=Cj0KCQjw9rSoBhCiARIsAFOiplmDMfINhQJO1sSdeNwhnJsD1pfKTZ_hz6xDYu2FwAfRNKHqPu0kaWEaAqdMEALw_wcB&gclsrc=aw.ds&hl=pt-br)
- [Clean Architecture]()
- [MVVM]()
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=pt-br)
- [Retrofit](https://square.github.io/retrofit/)
- [Coroutines](https://developer.android.com/kotlin/coroutines?hl=pt-br)
- [ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=pt-br)
- [LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=pt-br)
- [View Binding](https://developer.android.com/topic/libraries/view-binding?hl=pt-br)
- [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview?hl=pt-br)

## Executando o projeto

Crie um clone do projeto para você. Abra seu android studio e execute em um emulador de sua preferência.<br/>

**Observação**: Como o projeto utiliza de um Backend feito em Node JS por mim mesmo, então será necessário que você também clone esse projeto backend que se encontra [aqui](https://github.com/rquartaroli/desafio-nodejs-dailyDietAPI) e deixe-o executando em sua máquina para que a coleta e os registros dos dados funcionem.

Para executar o backend, abra o projeto e execute em seu terminal
```cl
npm install
```
Isso instalará todas as dependências necessárias para o projeto funcionar. Após concluir a instalação das dependências, execute agora em seu terminal:

```cl
npm run dev
```
Com isso você já terá o backend funcional e pronto para enviar e receber os dados da aplicação.<br/>

Esse projeto já esta configurado para rodar localmente junto ao projeto NODE JS citado anteriormente, mas caso tenha algum problema com o caminho da conexão. Você encontrará essa configuração na pasta **utils/ConstVariables**, e lá faça a configuração necessária para se conectar ao NODE JS

Após as configurações feitas de acordo, tudo estará funcionando normalmente e pronto para uso.

Feito por Rafael Quartaroli.

<br />