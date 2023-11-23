// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/routing.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "google/api/routing.pb.h"

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

const ::google::protobuf::Descriptor* RoutingRule_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  RoutingRule_reflection_ = NULL;
const ::google::protobuf::Descriptor* RoutingParameter_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  RoutingParameter_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_google_2fapi_2frouting_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_google_2fapi_2frouting_2eproto() {
  protobuf_AddDesc_google_2fapi_2frouting_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "google/api/routing.proto");
  GOOGLE_CHECK(file != NULL);
  RoutingRule_descriptor_ = file->message_type(0);
  static const int RoutingRule_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RoutingRule, routing_parameters_),
  };
  RoutingRule_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      RoutingRule_descriptor_,
      RoutingRule::internal_default_instance(),
      RoutingRule_offsets_,
      -1,
      -1,
      -1,
      sizeof(RoutingRule),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RoutingRule, _internal_metadata_));
  RoutingParameter_descriptor_ = file->message_type(1);
  static const int RoutingParameter_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RoutingParameter, field_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RoutingParameter, path_template_),
  };
  RoutingParameter_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      RoutingParameter_descriptor_,
      RoutingParameter::internal_default_instance(),
      RoutingParameter_offsets_,
      -1,
      -1,
      -1,
      sizeof(RoutingParameter),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RoutingParameter, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_google_2fapi_2frouting_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      RoutingRule_descriptor_, RoutingRule::internal_default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      RoutingParameter_descriptor_, RoutingParameter::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_google_2fapi_2frouting_2eproto() {
  RoutingRule_default_instance_.Shutdown();
  delete RoutingRule_reflection_;
  RoutingParameter_default_instance_.Shutdown();
  delete RoutingParameter_reflection_;
}

void protobuf_InitDefaults_google_2fapi_2frouting_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::protobuf_InitDefaults_google_2fprotobuf_2fdescriptor_2eproto();
  RoutingRule_default_instance_.DefaultConstruct();
  ::google::protobuf::internal::GetEmptyString();
  RoutingParameter_default_instance_.DefaultConstruct();
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::MethodOptions::internal_default_instance(),
    72295729, 11, false, false,
    ::google::api::RoutingRule::internal_default_instance());
  RoutingRule_default_instance_.get_mutable()->InitAsDefaultInstance();
  RoutingParameter_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_google_2fapi_2frouting_2eproto_once_);
void protobuf_InitDefaults_google_2fapi_2frouting_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_google_2fapi_2frouting_2eproto_once_,
                 &protobuf_InitDefaults_google_2fapi_2frouting_2eproto_impl);
}
void protobuf_AddDesc_google_2fapi_2frouting_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_google_2fapi_2frouting_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\030google/api/routing.proto\022\ngoogle.api\032 "
    "google/protobuf/descriptor.proto\"G\n\013Rout"
    "ingRule\0228\n\022routing_parameters\030\002 \003(\0132\034.go"
    "ogle.api.RoutingParameter\"8\n\020RoutingPara"
    "meter\022\r\n\005field\030\001 \001(\t\022\025\n\rpath_template\030\002 "
    "\001(\t:K\n\007routing\022\036.google.protobuf.MethodO"
    "ptions\030\261\312\274\" \001(\0132\027.google.api.RoutingRule"
    "Bj\n\016com.google.apiB\014RoutingProtoP\001ZAgoog"
    "le.golang.org/genproto/googleapis/api/an"
    "notations;annotations\242\002\004GAPIb\006proto3", 396);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "google/api/routing.proto", &protobuf_RegisterTypes);
  ::google::protobuf::protobuf_AddDesc_google_2fprotobuf_2fdescriptor_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_google_2fapi_2frouting_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_google_2fapi_2frouting_2eproto_once_);
void protobuf_AddDesc_google_2fapi_2frouting_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_google_2fapi_2frouting_2eproto_once_,
                 &protobuf_AddDesc_google_2fapi_2frouting_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_google_2fapi_2frouting_2eproto {
  StaticDescriptorInitializer_google_2fapi_2frouting_2eproto() {
    protobuf_AddDesc_google_2fapi_2frouting_2eproto();
  }
} static_descriptor_initializer_google_2fapi_2frouting_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int RoutingRule::kRoutingParametersFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

RoutingRule::RoutingRule()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_google_2fapi_2frouting_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:google.api.RoutingRule)
}

void RoutingRule::InitAsDefaultInstance() {
}

RoutingRule::RoutingRule(const RoutingRule& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:google.api.RoutingRule)
}

void RoutingRule::SharedCtor() {
  _cached_size_ = 0;
}

RoutingRule::~RoutingRule() {
  // @@protoc_insertion_point(destructor:google.api.RoutingRule)
  SharedDtor();
}

void RoutingRule::SharedDtor() {
}

void RoutingRule::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* RoutingRule::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return RoutingRule_descriptor_;
}

const RoutingRule& RoutingRule::default_instance() {
  protobuf_InitDefaults_google_2fapi_2frouting_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<RoutingRule> RoutingRule_default_instance_;

RoutingRule* RoutingRule::New(::google::protobuf::Arena* arena) const {
  RoutingRule* n = new RoutingRule;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void RoutingRule::Clear() {
// @@protoc_insertion_point(message_clear_start:google.api.RoutingRule)
  routing_parameters_.Clear();
}

bool RoutingRule::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:google.api.RoutingRule)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // repeated .google.api.RoutingParameter routing_parameters = 2;
      case 2: {
        if (tag == 18) {
          DO_(input->IncrementRecursionDepth());
         parse_loop_routing_parameters:
          DO_(::google::protobuf::internal::WireFormatLite::ReadMessageNoVirtualNoRecursionDepth(
                input, add_routing_parameters()));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_loop_routing_parameters;
        input->UnsafeDecrementRecursionDepth();
        if (input->ExpectAtEnd()) goto success;
        break;
      }

      default: {
      handle_unusual:
        if (tag == 0 ||
            ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          goto success;
        }
        DO_(::google::protobuf::internal::WireFormatLite::SkipField(input, tag));
        break;
      }
    }
  }
success:
  // @@protoc_insertion_point(parse_success:google.api.RoutingRule)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:google.api.RoutingRule)
  return false;
#undef DO_
}

void RoutingRule::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:google.api.RoutingRule)
  // repeated .google.api.RoutingParameter routing_parameters = 2;
  for (unsigned int i = 0, n = this->routing_parameters_size(); i < n; i++) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      2, this->routing_parameters(i), output);
  }

  // @@protoc_insertion_point(serialize_end:google.api.RoutingRule)
}

::google::protobuf::uint8* RoutingRule::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:google.api.RoutingRule)
  // repeated .google.api.RoutingParameter routing_parameters = 2;
  for (unsigned int i = 0, n = this->routing_parameters_size(); i < n; i++) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        2, this->routing_parameters(i), false, target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:google.api.RoutingRule)
  return target;
}

size_t RoutingRule::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:google.api.RoutingRule)
  size_t total_size = 0;

  // repeated .google.api.RoutingParameter routing_parameters = 2;
  {
    unsigned int count = this->routing_parameters_size();
    total_size += 1UL * count;
    for (unsigned int i = 0; i < count; i++) {
      total_size +=
        ::google::protobuf::internal::WireFormatLite::MessageSizeNoVirtual(
          this->routing_parameters(i));
    }
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void RoutingRule::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:google.api.RoutingRule)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const RoutingRule* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const RoutingRule>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:google.api.RoutingRule)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:google.api.RoutingRule)
    UnsafeMergeFrom(*source);
  }
}

void RoutingRule::MergeFrom(const RoutingRule& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:google.api.RoutingRule)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void RoutingRule::UnsafeMergeFrom(const RoutingRule& from) {
  GOOGLE_DCHECK(&from != this);
  routing_parameters_.MergeFrom(from.routing_parameters_);
}

void RoutingRule::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:google.api.RoutingRule)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void RoutingRule::CopyFrom(const RoutingRule& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:google.api.RoutingRule)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool RoutingRule::IsInitialized() const {

  return true;
}

void RoutingRule::Swap(RoutingRule* other) {
  if (other == this) return;
  InternalSwap(other);
}
void RoutingRule::InternalSwap(RoutingRule* other) {
  routing_parameters_.UnsafeArenaSwap(&other->routing_parameters_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata RoutingRule::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = RoutingRule_descriptor_;
  metadata.reflection = RoutingRule_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// RoutingRule

// repeated .google.api.RoutingParameter routing_parameters = 2;
int RoutingRule::routing_parameters_size() const {
  return routing_parameters_.size();
}
void RoutingRule::clear_routing_parameters() {
  routing_parameters_.Clear();
}
const ::google::api::RoutingParameter& RoutingRule::routing_parameters(int index) const {
  // @@protoc_insertion_point(field_get:google.api.RoutingRule.routing_parameters)
  return routing_parameters_.Get(index);
}
::google::api::RoutingParameter* RoutingRule::mutable_routing_parameters(int index) {
  // @@protoc_insertion_point(field_mutable:google.api.RoutingRule.routing_parameters)
  return routing_parameters_.Mutable(index);
}
::google::api::RoutingParameter* RoutingRule::add_routing_parameters() {
  // @@protoc_insertion_point(field_add:google.api.RoutingRule.routing_parameters)
  return routing_parameters_.Add();
}
::google::protobuf::RepeatedPtrField< ::google::api::RoutingParameter >*
RoutingRule::mutable_routing_parameters() {
  // @@protoc_insertion_point(field_mutable_list:google.api.RoutingRule.routing_parameters)
  return &routing_parameters_;
}
const ::google::protobuf::RepeatedPtrField< ::google::api::RoutingParameter >&
RoutingRule::routing_parameters() const {
  // @@protoc_insertion_point(field_list:google.api.RoutingRule.routing_parameters)
  return routing_parameters_;
}

inline const RoutingRule* RoutingRule::internal_default_instance() {
  return &RoutingRule_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int RoutingParameter::kFieldFieldNumber;
const int RoutingParameter::kPathTemplateFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

RoutingParameter::RoutingParameter()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_google_2fapi_2frouting_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:google.api.RoutingParameter)
}

void RoutingParameter::InitAsDefaultInstance() {
}

RoutingParameter::RoutingParameter(const RoutingParameter& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:google.api.RoutingParameter)
}

void RoutingParameter::SharedCtor() {
  field_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  path_template_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  _cached_size_ = 0;
}

RoutingParameter::~RoutingParameter() {
  // @@protoc_insertion_point(destructor:google.api.RoutingParameter)
  SharedDtor();
}

void RoutingParameter::SharedDtor() {
  field_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  path_template_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void RoutingParameter::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* RoutingParameter::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return RoutingParameter_descriptor_;
}

const RoutingParameter& RoutingParameter::default_instance() {
  protobuf_InitDefaults_google_2fapi_2frouting_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<RoutingParameter> RoutingParameter_default_instance_;

RoutingParameter* RoutingParameter::New(::google::protobuf::Arena* arena) const {
  RoutingParameter* n = new RoutingParameter;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void RoutingParameter::Clear() {
// @@protoc_insertion_point(message_clear_start:google.api.RoutingParameter)
  field_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  path_template_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

bool RoutingParameter::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:google.api.RoutingParameter)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional string field = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_field()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->field().data(), this->field().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "google.api.RoutingParameter.field"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_path_template;
        break;
      }

      // optional string path_template = 2;
      case 2: {
        if (tag == 18) {
         parse_path_template:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_path_template()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->path_template().data(), this->path_template().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "google.api.RoutingParameter.path_template"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectAtEnd()) goto success;
        break;
      }

      default: {
      handle_unusual:
        if (tag == 0 ||
            ::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          goto success;
        }
        DO_(::google::protobuf::internal::WireFormatLite::SkipField(input, tag));
        break;
      }
    }
  }
success:
  // @@protoc_insertion_point(parse_success:google.api.RoutingParameter)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:google.api.RoutingParameter)
  return false;
#undef DO_
}

void RoutingParameter::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:google.api.RoutingParameter)
  // optional string field = 1;
  if (this->field().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->field().data(), this->field().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.RoutingParameter.field");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->field(), output);
  }

  // optional string path_template = 2;
  if (this->path_template().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->path_template().data(), this->path_template().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.RoutingParameter.path_template");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->path_template(), output);
  }

  // @@protoc_insertion_point(serialize_end:google.api.RoutingParameter)
}

::google::protobuf::uint8* RoutingParameter::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:google.api.RoutingParameter)
  // optional string field = 1;
  if (this->field().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->field().data(), this->field().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.RoutingParameter.field");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->field(), target);
  }

  // optional string path_template = 2;
  if (this->path_template().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->path_template().data(), this->path_template().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.RoutingParameter.path_template");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->path_template(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:google.api.RoutingParameter)
  return target;
}

size_t RoutingParameter::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:google.api.RoutingParameter)
  size_t total_size = 0;

  // optional string field = 1;
  if (this->field().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->field());
  }

  // optional string path_template = 2;
  if (this->path_template().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->path_template());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void RoutingParameter::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:google.api.RoutingParameter)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const RoutingParameter* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const RoutingParameter>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:google.api.RoutingParameter)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:google.api.RoutingParameter)
    UnsafeMergeFrom(*source);
  }
}

void RoutingParameter::MergeFrom(const RoutingParameter& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:google.api.RoutingParameter)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void RoutingParameter::UnsafeMergeFrom(const RoutingParameter& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.field().size() > 0) {

    field_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.field_);
  }
  if (from.path_template().size() > 0) {

    path_template_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.path_template_);
  }
}

void RoutingParameter::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:google.api.RoutingParameter)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void RoutingParameter::CopyFrom(const RoutingParameter& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:google.api.RoutingParameter)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool RoutingParameter::IsInitialized() const {

  return true;
}

void RoutingParameter::Swap(RoutingParameter* other) {
  if (other == this) return;
  InternalSwap(other);
}
void RoutingParameter::InternalSwap(RoutingParameter* other) {
  field_.Swap(&other->field_);
  path_template_.Swap(&other->path_template_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata RoutingParameter::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = RoutingParameter_descriptor_;
  metadata.reflection = RoutingParameter_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// RoutingParameter

// optional string field = 1;
void RoutingParameter::clear_field() {
  field_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& RoutingParameter::field() const {
  // @@protoc_insertion_point(field_get:google.api.RoutingParameter.field)
  return field_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void RoutingParameter::set_field(const ::std::string& value) {
  
  field_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:google.api.RoutingParameter.field)
}
void RoutingParameter::set_field(const char* value) {
  
  field_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:google.api.RoutingParameter.field)
}
void RoutingParameter::set_field(const char* value, size_t size) {
  
  field_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:google.api.RoutingParameter.field)
}
::std::string* RoutingParameter::mutable_field() {
  
  // @@protoc_insertion_point(field_mutable:google.api.RoutingParameter.field)
  return field_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* RoutingParameter::release_field() {
  // @@protoc_insertion_point(field_release:google.api.RoutingParameter.field)
  
  return field_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void RoutingParameter::set_allocated_field(::std::string* field) {
  if (field != NULL) {
    
  } else {
    
  }
  field_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), field);
  // @@protoc_insertion_point(field_set_allocated:google.api.RoutingParameter.field)
}

// optional string path_template = 2;
void RoutingParameter::clear_path_template() {
  path_template_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& RoutingParameter::path_template() const {
  // @@protoc_insertion_point(field_get:google.api.RoutingParameter.path_template)
  return path_template_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void RoutingParameter::set_path_template(const ::std::string& value) {
  
  path_template_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:google.api.RoutingParameter.path_template)
}
void RoutingParameter::set_path_template(const char* value) {
  
  path_template_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:google.api.RoutingParameter.path_template)
}
void RoutingParameter::set_path_template(const char* value, size_t size) {
  
  path_template_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:google.api.RoutingParameter.path_template)
}
::std::string* RoutingParameter::mutable_path_template() {
  
  // @@protoc_insertion_point(field_mutable:google.api.RoutingParameter.path_template)
  return path_template_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* RoutingParameter::release_path_template() {
  // @@protoc_insertion_point(field_release:google.api.RoutingParameter.path_template)
  
  return path_template_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void RoutingParameter::set_allocated_path_template(::std::string* path_template) {
  if (path_template != NULL) {
    
  } else {
    
  }
  path_template_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), path_template);
  // @@protoc_insertion_point(field_set_allocated:google.api.RoutingParameter.path_template)
}

inline const RoutingParameter* RoutingParameter::internal_default_instance() {
  return &RoutingParameter_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::MethodOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::RoutingRule >, 11, false >
  routing(kRoutingFieldNumber, *::google::api::RoutingRule::internal_default_instance());

// @@protoc_insertion_point(namespace_scope)

}  // namespace api
}  // namespace google

// @@protoc_insertion_point(global_scope)
