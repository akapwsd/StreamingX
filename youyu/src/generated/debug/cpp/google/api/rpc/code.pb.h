// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/rpc/code.proto

#ifndef PROTOBUF_google_2fapi_2frpc_2fcode_2eproto__INCLUDED
#define PROTOBUF_google_2fapi_2frpc_2fcode_2eproto__INCLUDED

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
// @@protoc_insertion_point(includes)

namespace google {
namespace rpc {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_google_2fapi_2frpc_2fcode_2eproto();
void protobuf_InitDefaults_google_2fapi_2frpc_2fcode_2eproto();
void protobuf_AssignDesc_google_2fapi_2frpc_2fcode_2eproto();
void protobuf_ShutdownFile_google_2fapi_2frpc_2fcode_2eproto();


enum Code {
  OK = 0,
  CANCELLED = 1,
  UNKNOWN = 2,
  INVALID_ARGUMENT = 3,
  DEADLINE_EXCEEDED = 4,
  NOT_FOUND = 5,
  ALREADY_EXISTS = 6,
  PERMISSION_DENIED = 7,
  UNAUTHENTICATED = 16,
  RESOURCE_EXHAUSTED = 8,
  FAILED_PRECONDITION = 9,
  ABORTED = 10,
  OUT_OF_RANGE = 11,
  UNIMPLEMENTED = 12,
  INTERNAL = 13,
  UNAVAILABLE = 14,
  DATA_LOSS = 15,
  Code_INT_MIN_SENTINEL_DO_NOT_USE_ = ::google::protobuf::kint32min,
  Code_INT_MAX_SENTINEL_DO_NOT_USE_ = ::google::protobuf::kint32max
};
bool Code_IsValid(int value);
const Code Code_MIN = OK;
const Code Code_MAX = UNAUTHENTICATED;
const int Code_ARRAYSIZE = Code_MAX + 1;

const ::google::protobuf::EnumDescriptor* Code_descriptor();
inline const ::std::string& Code_Name(Code value) {
  return ::google::protobuf::internal::NameOfEnum(
    Code_descriptor(), value);
}
inline bool Code_Parse(
    const ::std::string& name, Code* value) {
  return ::google::protobuf::internal::ParseNamedEnum<Code>(
    Code_descriptor(), name, value);
}
// ===================================================================


// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace rpc
}  // namespace google

#ifndef SWIG
namespace google {
namespace protobuf {

template <> struct is_proto_enum< ::google::rpc::Code> : ::google::protobuf::internal::true_type {};
template <>
inline const EnumDescriptor* GetEnumDescriptor< ::google::rpc::Code>() {
  return ::google::rpc::Code_descriptor();
}

}  // namespace protobuf
}  // namespace google
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_google_2fapi_2frpc_2fcode_2eproto__INCLUDED
