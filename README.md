android_packages_apps_MsimEnDe
==============================

Helps to switch between single and dual sim only for GT-I9082. Flexible code to show only if GT-I9082


To add this to your ROM:

- your build.prop must contain ro.aogp.msimende property
- prop value:
* 1 to hide on boot(reason:may be its not GT-I9082)
* 0 show up on every boot
- seperate flashable zip is available on website