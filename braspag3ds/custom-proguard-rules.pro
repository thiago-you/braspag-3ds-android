-verbose
-printusage usage.txt
-printmapping out.map

## keep every public class in this library from being removed or renamed
-keep public class br.com.braspag.Braspag3ds {
    *;
}

-keep public class br.com.braspag.customization.* {
    *;
}

-keep public class br.com.braspag.data.* {
    *;
}

-keep class org.bouncycastle.**
-keep class com.nimbusds.**