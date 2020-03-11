import sbtprotoc.ProtocPlugin.autoImport._

val grpcVersion = "1.26.0"

inThisBuild(Seq(
    scalaVersion := "2.13.1",
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    updateOptions := updateOptions.value.withLatestSnapshots(false),
))

lazy val `common-protos` = (project in file("modules") / "common")
  .settings(
    PB.includePaths in Compile := Seq(
      target.value / "protobuf_external",
      (ThisBuild / baseDirectory).value / "googleapis",
    ),
    // scalapbCodeGeneratorOptions += CodeGeneratorOption.FlatPackage,
    // Compile / PB.targets := scalapbCodeGenerators.value,
    PB.protoSources in Compile := Seq(
    //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "api",
    //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "cloud" / "audit",
    //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "logging" / "type",
    //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "longrunning",
    //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "rpc",
    //   (ThisBuild / baseDirectory).value /  "googleapis" / "google" / "type"
    ),

    PB.targets in Compile := Seq(
      scalapb.gen(grpc = true) -> (sourceManaged in Compile).value,
      scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value,
    ),

    libraryDependencies ++= Seq(
        "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion % "protobuf",
        "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
        // "com.google.protobuf" % "protobuf-java" % "3.11.3" % "protobuf",
        "io.grpc" % "grpc-netty" % grpcVersion
    )
  )


lazy val firestore = (project in file("modules") / "firestore")
  .dependsOn(`common-protos`)
  .settings(
    PB.includePaths in Compile := Seq(
      target.value / "protobuf_external",
      (ThisBuild / baseDirectory).value / "googleapis",
    ),
    // scalapbCodeGeneratorOptions += CodeGeneratorOption.FlatPackage,
    // Compile / PB.targets := scalapbCodeGenerators.value,
    PB.protoSources in Compile := Seq(
      (ThisBuild / baseDirectory).value / "googleapis" / "google" / "firestore" / "v1"
    ),

    PB.targets in Compile := Seq(
        scalapb.gen(grpc = true, flatPackage = true) -> (sourceManaged in Compile).value,
        scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value,
    ),
  )