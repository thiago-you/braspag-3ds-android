# Braspag - Cielo 3DS

## Introdução

Com o objetivo de minimizar o índice de fraude sem prejudicar a taxa de conversão, a indústria de meio de pagamento desenvolveu um novo padrão de autenticação, chamado EMV 3DS , ou também chamado de 3DS 2.0.

## Últimas Alterações
- Migração do repositório JCenter (descontinuado) para o Jitpack.io
- Migração das dependências de Support (Jetifier) para Androidx
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

### Documentação

Link para a documentação do SDK Android para o 3ds: https://braspag.github.io//manual/integracao-sdk-android
