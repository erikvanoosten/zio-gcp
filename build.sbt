ThisBuild / organization := "nl.grons"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / Compile / doc / sources := List()

//inThisBuild(Seq(
//    scalaVersion := "2.13.6"
////    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
////    updateOptions := updateOptions.value.withLatestSnapshots(false),
//))

val importProtoPaths = Seq(
  file("googleapis"),
  file("protobuf") / "src"
)

//val `common-protos` = project
//  .in(file("common-protos"))
//  .settings(
//    Compile / PB.protoSources := Seq(
//      file("googleapis") / "google" / "api",
//      file("googleapis") / "google" / "rpc",
//      file("googleapis") / "google" / "protobuf"
////      file("protobuf") / "src"
//    ),
//    Compile / PB.includePaths := importProtoPaths,
//    Compile / PB.targets := Seq(
//      scalapb.gen(grpc = true) -> (Compile / sourceManaged).value / "scalapb",
//      scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
//    ),
////    libraryDependencies ++= Seq(
////      "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb
////        .compiler
////        .Version
////        .scalapbVersion % "protobuf"
////    ),
//    libraryDependencies ++= Seq(
//      "io.grpc" % "grpc-netty" % "1.41.0",
//      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
////      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.5.0-2" % "protobuf",
////      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.5.0-2"
//    )
//  )

lazy val `pubsub-protos` = project
  .in(file("pubsub-protos"))
  .settings(
    Compile / PB.protoSources := Seq(
      file("googleapis") / "google" / "pubsub" / "v1"
    ),
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true) -> (Compile / sourceManaged).value / "scalapb",
      scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
    ),
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % "1.41.0",
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.5.0-2" % "protobuf",
      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.5.0-2"
    )
  )

lazy val `firestore-protos` = project
  .in(file("firestore-protos"))
  .settings(
    Compile / PB.protoSources := Seq(
      file("googleapis") / "google" / "firestore" / "v1"
    ),
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true) -> (Compile / sourceManaged).value / "scalapb",
      scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
    ),
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % "1.41.0",
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.5.0-2" % "protobuf",
      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.5.0-2"
    )
  )

//lazy val `protos2` = (project in file("protos") / "common")
//  .settings(
//    PB.targets in Compile := Seq(
//      scalapb.gen(grpc = true) -> (sourceManaged in Compile).value / "scalapb",
//      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value / "scalapb"
//    ),
//    PB.includePaths in Compile := Seq(
//      target.value / "protobuf_external",
//      (ThisBuild / baseDirectory).value / "googleapis",
//    ),
//    // scalapbCodeGeneratorOptions += CodeGeneratorOption.FlatPackage,
//    // Compile / PB.targets := scalapbCodeGenerators.value,
//    PB.protoSources in Compile := Seq(
//      //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "api",
//      //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "cloud" / "audit",
//      //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "logging" / "type",
//      //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "longrunning",
//      //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "rpc",
//      //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "type"
//    ),
//    PB.targets in Compile := Seq(
//      scalapb.gen(grpc = true) -> (sourceManaged in Compile).value,
//      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value,
//    ),
//    libraryDependencies ++= Seq(
//      "io.grpc" % "grpc-netty" % "1.41.0",
//      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
//    )
//  )

//lazy val firestore = (project in file("modules") / "firestore")
//  .dependsOn(`common-protos`)
//  .settings(
//    PB.includePaths in Compile := Seq(
//      target.value / "protobuf_external",
//      (ThisBuild / baseDirectory).value / "googleapis",
//    ),
//    // scalapbCodeGeneratorOptions += CodeGeneratorOption.FlatPackage,
//    // Compile / PB.targets := scalapbCodeGenerators.value,
//    PB.protoSources in Compile := Seq(
//      (ThisBuild / baseDirectory).value / "googleapis" / "google" / "firestore" / "v1"
//    ),
//
//    PB.targets in Compile := Seq(
//        scalapb.gen(grpc = true, flatPackage = true) -> (sourceManaged in Compile).value,
//        scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value,
//    ),
//  )

val `zio-gcp` = project
  .in(file("."))
  .aggregate(
//    `common-protos`,
    `pubsub-protos`,
    `firestore-protos`
  )
  .settings(
    crossScalaVersions := Nil,
    publish / skip := true
  )
