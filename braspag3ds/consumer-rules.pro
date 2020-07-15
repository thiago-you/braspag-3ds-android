#noinspection ShrinkerUnresolvedReference
-keep class br.com.braspag.data.** { *; }
-keep class br.com.braspag.internal.data.** { *; }
-keepclasseswithmembernames class br.com.braspag.data.** { *; }
-keepclasseswithmembernames class br.com.braspag.internal.data.** { *; }

-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-keep class com.cardinalcommerce.dependencies.internal.bouncycastle.**
-keep class com.cardinalcommerce.dependencies.internal.nimbusds.**