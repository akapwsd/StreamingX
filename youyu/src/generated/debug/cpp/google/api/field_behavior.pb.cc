// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/field_behavior.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "google/api/field_behavior.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/common.h>
#include <google/protobuf/stubs/port.h>
#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)

namespace google {
namespace api {

namespace {

const ::google::protobuf::EnumDescriptor* FieldBehavior_descriptor_ = NULL;

}  // namespace


void protobuf_AssignDesc_google_2fapi_2ffield_5fbehavior_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_google_2fapi_2ffield_5fbehavior_2eproto() {
  protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "google/api/field_behavior.proto");
  GOOGLE_CHECK(file != NULL);
  FieldBehavior_descriptor_ = file->enum_type(0);
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_google_2fapi_2ffield_5fbehavior_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
}

}  // namespace

void protobuf_ShutdownFile_google_2fapi_2ffield_5fbehavior_2eproto() {
}

void protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::protobuf_InitDefaults_google_2fprotobuf_2fdescriptor_2eproto();
  ::google::protobuf::internal::ExtensionSet::RegisterEnumExtension(
    ::google::protobuf::FieldOptions::internal_default_instance(),
    1052, 14, true, false,
    &::google::api::FieldBehavior_IsValid);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto_once_);
void protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto_once_,
                 &protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto_impl);
}
void protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\037google/api/field_behavior.proto\022\ngoogl"
    "e.api\032 google/protobuf/descriptor.proto*"
    "\246\001\n\rFieldBehavior\022\036\n\032FIELD_BEHAVIOR_UNSP"
    "ECIFIED\020\000\022\014\n\010OPTIONAL\020\001\022\014\n\010REQUIRED\020\002\022\017\n"
    "\013OUTPUT_ONLY\020\003\022\016\n\nINPUT_ONLY\020\004\022\r\n\tIMMUTA"
    "BLE\020\005\022\022\n\016UNORDERED_LIST\020\006\022\025\n\021NON_EMPTY_D"
    "EFAULT\020\007:Q\n\016field_behavior\022\035.google.prot"
    "obuf.FieldOptions\030\234\010 \003(\0162\031.google.api.Fi"
    "eldBehaviorBp\n\016com.google.apiB\022FieldBeha"
    "viorProtoP\001ZAgoogle.golang.org/genproto/"
    "googleapis/api/annotations;annotations\242\002"
    "\004GAPIb\006proto3", 453);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "google/api/field_behavior.proto", &protobuf_RegisterTypes);
  ::google::protobuf::protobuf_AddDesc_google_2fprotobuf_2fdescriptor_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_google_2fapi_2ffield_5fbehavior_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto_once_);
void protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto_once_,
                 &protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_google_2fapi_2ffield_5fbehavior_2eproto {
  StaticDescriptorInitializer_google_2fapi_2ffield_5fbehavior_2eproto() {
    protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto();
  }
} static_descriptor_initializer_google_2fapi_2ffield_5fbehavior_2eproto_;
const ::google::protobuf::EnumDescriptor* FieldBehavior_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return FieldBehavior_descriptor_;
}
bool FieldBehavior_IsValid(int value) {
  switch (value) {
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
      return true;
    default:
      return false;
  }
}

::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::FieldOptions,
    ::google::protobuf::internal::RepeatedEnumTypeTraits< ::google::api::FieldBehavior, ::google::api::FieldBehavior_IsValid>, 14, false >
  field_behavior(kFieldBehaviorFieldNumber, static_cast< ::google::api::FieldBehavior >(0));

// @@protoc_insertion_point(namespace_scope)

}  // namespace api
}  // namespace google

// @@protoc_insertion_point(global_scope)
