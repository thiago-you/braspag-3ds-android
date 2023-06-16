# Braspag - Cielo 3DS

## Introdução

Com o objetivo de minimizar o índice de fraude sem prejudicar a taxa de conversão, a indústria de meio de pagamento desenvolveu um novo padrão de autenticação, chamado EMV 3DS , ou também chamado de 3DS 2.0.

## Últimas Alterações
- Migração do repositório JCenter (descontinuado) para o Jitpack.io
- Atualização SDK suportado e compilado para a API 33
- Atualização das depêndencias do Gradle
- Removido plugins depreciados (kotlin synthetics)
- Atualizado versão do Gradle e do Kotlin

### Como importar o SDK

Para utilizar, adicione o respositório do Jitpack.io no `build.gradle` raíz do projeto:

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Depois, adicione a implementação da dependência no `build.gradle` do seu módulo (app):

```gradle
dependencies {
    implementation 'com.github.thiago-you:braspag-3ds-android:1.0.0'
}
```

Para utilizar o módulo Cardinal, também é necessário realizar o download da biblioteca `.aar` e realizar a importação como módulo dentro do projeto.

A importação pode ser realizada adicionando os memos arquivos que estão importados neste projeto, no módulo: `lib-cardinal`

**Link:** https://github.com/thiago-you/braspag-3ds-android/tree/master/lib-cardinal

É necessário adicionar os 2 arquivos dentro do módulo no seu projeto:

- build.gradle
- cardinalmobilesdk-2.2.5-4.aar

Após isso, basta adicionar a implementação dentro do `Gradle` da sua aplicação:

```gradle
dependencies {
    // cardinal lib aar import
    implementation fileTree(include: ['*.aar', '*jar'], dir: 'libs')
    implementation project(path: ':lib-cardinal', configuration: 'default')
}
```

### Jetifier
A configuração do `Jetifier` foi ativada para poder utilizar a versão atual da biblioteca `Cardinal`. Essa configuração força a compatibilidade com as bibliotecas legadas de `support` do Android.

### Documentação

Link para a documentação do SDK Android para o 3ds: https://braspag.github.io//manual/integracao-sdk-android
