// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/field_behavior.proto

#ifndef PROTOBUF_google_2fapi_2ffield_5fbehavior_2eproto__INCLUDED
#define PROTOBUF_google_2fapi_2ffield_5fbehavior_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3001000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3001000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/generated_enum_reflection.h>
#include <google/protobuf/descriptor.pb.h>
// @@protoc_insertion_point(includes)

namespace google {
namespace api {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_google_2fapi_2ffield_5fbehavior_2eproto();
void protobuf_InitDefaults_google_2fapi_2ffield_5fbehavior_2eproto();
void protobuf_AssignDesc_google_2fapi_2ffield_5fbehavior_2eproto();
void protobuf_ShutdownFile_google_2fapi_2ffield_5fbehavior_2eproto();


enum FieldBehavior {
  FIELD_BEHAVIOR_UNSPECIFIED = 0,
  OPTIONAL = 1,
  REQUIRED = 2,
  OUTPUT_ONLY = 3,
  INPUT_ONLY = 4,
  IMMUTABLE = 5,
  UNORDERED_LIST = 6,
  NON_EMPTY_DEFAULT = 7,
  FieldBehavior_INT_MIN_SENTINEL_DO_NOT_USE_ = ::google::protobuf::kint32min,
  FieldBehavior_INT_MAX_SENTINEL_DO_NOT_USE_ = ::google::protobuf::kint32max
};
bool FieldBehavior_IsValid(int value);
const FieldBehavior FieldBehavior_MIN = FIELD_BEHAVIOR_UNSPECIFIED;
const FieldBehavior FieldBehavior_MAX = NON_EMPTY_DEFAULT;
const int FieldBehavior_ARRAYSIZE = FieldBehavior_MAX + 1;

const ::google::protobuf::EnumDescriptor* FieldBehavior_descriptor();
inline const ::std::string& FieldBehavior_Name(FieldBehavior value) {
  return ::google::protobuf::internal::NameOfEnum(
    FieldBehavior_descriptor(), value);
}
inline bool FieldBehavior_Parse(
    const ::std::string& name, FieldBehavior* value) {
  return ::google::protobuf::internal::ParseNamedEnum<FieldBehavior>(
    FieldBehavior_descriptor(), name, value);
}
// ===================================================================


// ===================================================================

static const int kFieldBehaviorFieldNumber = 1052;
extern ::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::FieldOptions,
    ::google::protobuf::internal::RepeatedEnumTypeTraits< ::google::api::FieldBehavior, ::google::api::FieldBehavior_IsValid>, 14, false >
  field_behavior;

// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace api
}  // namespace google

#ifndef SWIG
namespace google {
namespace protobuf {

template <> struct is_proto_enum< ::google::api::FieldBehavior> : ::google::protobuf::internal::true_type {};
template <>
inline const EnumDescriptor* GetEnumDescriptor< ::google::api::FieldBehavior>() {
  return ::google::api::FieldBehavior_descriptor();
}

}  // namespace protobuf
}  // namespace google
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_google_2fapi_2ffield_5fbehavior_2eproto__INCLUDED
