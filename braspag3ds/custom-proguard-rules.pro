-verbose
-printusage usage.txt
-printmapping out.map

## keep every public class in this library from being removed or renamed
-keep public class * {
    public protected *;
}

-keep public class br.com.braspag.Braspag3ds {
    public protected *;
}

-keep public class br.com.braspag.customization.* {
    public protected *;
}

-keep public class br.com.braspag.data.* {
    public protected *;
}

-keep class br.com.braspag.internal.data.* {
    *;
}

-keep class org.bouncycastle.**
-keep class com.nimbusds.**