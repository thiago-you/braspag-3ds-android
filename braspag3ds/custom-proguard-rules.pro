-verbose
-printusage usage.txt
-printmapping out.map

## keep every public class in this library from being removed or renamed
-keep public class * {
    public protected *;
}