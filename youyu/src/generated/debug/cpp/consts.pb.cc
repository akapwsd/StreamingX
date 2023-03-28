// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: consts.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "consts.pb.h"

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

namespace batprotobuf {

namespace {

const ::google::protobuf::EnumDescriptor* channelState_descriptor_ = NULL;
const ::google::protobuf::EnumDescriptor* channelUserState_descriptor_ = NULL;

}  // namespace


void protobuf_AssignDesc_consts_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_consts_2eproto() {
  protobuf_AddDesc_consts_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "consts.proto");
  GOOGLE_CHECK(file != NULL);
  channelState_descriptor_ = file->enum_type(0);
  channelUserState_descriptor_ = file->enum_type(1);
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_consts_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
}

}  // namespace

void protobuf_ShutdownFile_consts_2eproto() {
}

void protobuf_InitDefaults_consts_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_consts_2eproto_once_);
void protobuf_InitDefaults_consts_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_consts_2eproto_once_,
                 &protobuf_InitDefaults_consts_2eproto_impl);
}
void protobuf_AddDesc_consts_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_consts_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\014consts.proto\022\013batprotobuf*n\n\014channelSt"
    "ate\022\026\n\022ChannelStateClosed\020\000\022\024\n\020ChannelSt"
    "ateFree\020\001\022\024\n\020ChannelStateBusy\020\002\022\032\n\026Chann"
    "elStateRefreshing\020\003*F\n\020channelUserState\022"
    "\030\n\024ChannelUserStateJoin\020\000\022\030\n\024ChannelUser"
    "StateQuit\020\001B\rZ\013.;uyujoyapib\006proto3", 234);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "consts.proto", &protobuf_RegisterTypes);
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_consts_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_consts_2eproto_once_);
void protobuf_AddDesc_consts_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_consts_2eproto_once_,
                 &protobuf_AddDesc_consts_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_consts_2eproto {
  StaticDescriptorInitializer_consts_2eproto() {
    protobuf_AddDesc_consts_2eproto();
  }
} static_descriptor_initializer_consts_2eproto_;
const ::google::protobuf::EnumDescriptor* channelState_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return channelState_descriptor_;
}
bool channelState_IsValid(int value) {
  switch (value) {
    case 0:
    case 1:
    case 2:
    case 3:
      return true;
    default:
      return false;
  }
}

const ::google::protobuf::EnumDescriptor* channelUserState_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return channelUserState_descriptor_;
}
bool channelUserState_IsValid(int value) {
  switch (value) {
    case 0:
    case 1:
      return true;
    default:
      return false;
  }
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace batprotobuf

// @@protoc_insertion_point(global_scope)