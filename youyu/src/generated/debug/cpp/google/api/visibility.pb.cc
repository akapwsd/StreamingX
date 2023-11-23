// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/visibility.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "google/api/visibility.pb.h"

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

const ::google::protobuf::Descriptor* Visibility_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  Visibility_reflection_ = NULL;
const ::google::protobuf::Descriptor* VisibilityRule_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  VisibilityRule_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_google_2fapi_2fvisibility_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_google_2fapi_2fvisibility_2eproto() {
  protobuf_AddDesc_google_2fapi_2fvisibility_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "google/api/visibility.proto");
  GOOGLE_CHECK(file != NULL);
  Visibility_descriptor_ = file->message_type(0);
  static const int Visibility_offsets_[1] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Visibility, rules_),
  };
  Visibility_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      Visibility_descriptor_,
      Visibility::internal_default_instance(),
      Visibility_offsets_,
      -1,
      -1,
      -1,
      sizeof(Visibility),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Visibility, _internal_metadata_));
  VisibilityRule_descriptor_ = file->message_type(1);
  static const int VisibilityRule_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(VisibilityRule, selector_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(VisibilityRule, restriction_),
  };
  VisibilityRule_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      VisibilityRule_descriptor_,
      VisibilityRule::internal_default_instance(),
      VisibilityRule_offsets_,
      -1,
      -1,
      -1,
      sizeof(VisibilityRule),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(VisibilityRule, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_google_2fapi_2fvisibility_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      Visibility_descriptor_, Visibility::internal_default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      VisibilityRule_descriptor_, VisibilityRule::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_google_2fapi_2fvisibility_2eproto() {
  Visibility_default_instance_.Shutdown();
  delete Visibility_reflection_;
  VisibilityRule_default_instance_.Shutdown();
  delete VisibilityRule_reflection_;
}

void protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::protobuf_InitDefaults_google_2fprotobuf_2fdescriptor_2eproto();
  Visibility_default_instance_.DefaultConstruct();
  ::google::protobuf::internal::GetEmptyString();
  VisibilityRule_default_instance_.DefaultConstruct();
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::EnumOptions::internal_default_instance(),
    72295727, 11, false, false,
    ::google::api::VisibilityRule::internal_default_instance());
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::EnumValueOptions::internal_default_instance(),
    72295727, 11, false, false,
    ::google::api::VisibilityRule::internal_default_instance());
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::FieldOptions::internal_default_instance(),
    72295727, 11, false, false,
    ::google::api::VisibilityRule::internal_default_instance());
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::MessageOptions::internal_default_instance(),
    72295727, 11, false, false,
    ::google::api::VisibilityRule::internal_default_instance());
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::MethodOptions::internal_default_instance(),
    72295727, 11, false, false,
    ::google::api::VisibilityRule::internal_default_instance());
  ::google::protobuf::internal::ExtensionSet::RegisterMessageExtension(
    ::google::protobuf::ServiceOptions::internal_default_instance(),
    72295727, 11, false, false,
    ::google::api::VisibilityRule::internal_default_instance());
  Visibility_default_instance_.get_mutable()->InitAsDefaultInstance();
  VisibilityRule_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto_once_);
void protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto_once_,
                 &protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto_impl);
}
void protobuf_AddDesc_google_2fapi_2fvisibility_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\033google/api/visibility.proto\022\ngoogle.ap"
    "i\032 google/protobuf/descriptor.proto\"7\n\nV"
    "isibility\022)\n\005rules\030\001 \003(\0132\032.google.api.Vi"
    "sibilityRule\"7\n\016VisibilityRule\022\020\n\010select"
    "or\030\001 \001(\t\022\023\n\013restriction\030\002 \001(\t:T\n\017enum_vi"
    "sibility\022\034.google.protobuf.EnumOptions\030\257"
    "\312\274\" \001(\0132\032.google.api.VisibilityRule:Z\n\020v"
    "alue_visibility\022!.google.protobuf.EnumVa"
    "lueOptions\030\257\312\274\" \001(\0132\032.google.api.Visibil"
    "ityRule:V\n\020field_visibility\022\035.google.pro"
    "tobuf.FieldOptions\030\257\312\274\" \001(\0132\032.google.api"
    ".VisibilityRule:Z\n\022message_visibility\022\037."
    "google.protobuf.MessageOptions\030\257\312\274\" \001(\0132"
    "\032.google.api.VisibilityRule:X\n\021method_vi"
    "sibility\022\036.google.protobuf.MethodOptions"
    "\030\257\312\274\" \001(\0132\032.google.api.VisibilityRule:V\n"
    "\016api_visibility\022\037.google.protobuf.Servic"
    "eOptions\030\257\312\274\" \001(\0132\032.google.api.Visibilit"
    "yRuleBn\n\016com.google.apiB\017VisibilityProto"
    "P\001Z\?google.golang.org/genproto/googleapi"
    "s/api/visibility;visibility\370\001\001\242\002\004GAPIb\006p"
    "roto3", 845);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "google/api/visibility.proto", &protobuf_RegisterTypes);
  ::google::protobuf::protobuf_AddDesc_google_2fprotobuf_2fdescriptor_2eproto();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_google_2fapi_2fvisibility_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_google_2fapi_2fvisibility_2eproto_once_);
void protobuf_AddDesc_google_2fapi_2fvisibility_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_google_2fapi_2fvisibility_2eproto_once_,
                 &protobuf_AddDesc_google_2fapi_2fvisibility_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_google_2fapi_2fvisibility_2eproto {
  StaticDescriptorInitializer_google_2fapi_2fvisibility_2eproto() {
    protobuf_AddDesc_google_2fapi_2fvisibility_2eproto();
  }
} static_descriptor_initializer_google_2fapi_2fvisibility_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int Visibility::kRulesFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

Visibility::Visibility()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:google.api.Visibility)
}
Visibility::Visibility(::google::protobuf::Arena* arena)
  : ::google::protobuf::Message(),
  _internal_metadata_(arena),
  rules_(arena) {
#ifdef GOOGLE_PROTOBUF_NO_STATIC_INITIALIZER
  protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
#endif  // GOOGLE_PROTOBUF_NO_STATIC_INITIALIZER
  SharedCtor();
  RegisterArenaDtor(arena);
  // @@protoc_insertion_point(arena_constructor:google.api.Visibility)
}

void Visibility::InitAsDefaultInstance() {
}

Visibility::Visibility(const Visibility& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:google.api.Visibility)
}

void Visibility::SharedCtor() {
  _cached_size_ = 0;
}

Visibility::~Visibility() {
  // @@protoc_insertion_point(destructor:google.api.Visibility)
  SharedDtor();
}

void Visibility::SharedDtor() {
  ::google::protobuf::Arena* arena = GetArenaNoVirtual();
  if (arena != NULL) {
    return;
  }

}

void Visibility::ArenaDtor(void* object) {
  Visibility* _this = reinterpret_cast< Visibility* >(object);
  (void)_this;
}
void Visibility::RegisterArenaDtor(::google::protobuf::Arena* arena) {
}
void Visibility::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* Visibility::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return Visibility_descriptor_;
}

const Visibility& Visibility::default_instance() {
  protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<Visibility> Visibility_default_instance_;

Visibility* Visibility::New(::google::protobuf::Arena* arena) const {
  return ::google::protobuf::Arena::CreateMessage<Visibility>(arena);
}

void Visibility::Clear() {
// @@protoc_insertion_point(message_clear_start:google.api.Visibility)
  rules_.Clear();
}

bool Visibility::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:google.api.Visibility)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // repeated .google.api.VisibilityRule rules = 1;
      case 1: {
        if (tag == 10) {
          DO_(input->IncrementRecursionDepth());
         parse_loop_rules:
          DO_(::google::protobuf::internal::WireFormatLite::ReadMessageNoVirtualNoRecursionDepth(
                input, add_rules()));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(10)) goto parse_loop_rules;
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
  // @@protoc_insertion_point(parse_success:google.api.Visibility)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:google.api.Visibility)
  return false;
#undef DO_
}

void Visibility::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:google.api.Visibility)
  // repeated .google.api.VisibilityRule rules = 1;
  for (unsigned int i = 0, n = this->rules_size(); i < n; i++) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      1, this->rules(i), output);
  }

  // @@protoc_insertion_point(serialize_end:google.api.Visibility)
}

::google::protobuf::uint8* Visibility::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:google.api.Visibility)
  // repeated .google.api.VisibilityRule rules = 1;
  for (unsigned int i = 0, n = this->rules_size(); i < n; i++) {
    target = ::google::protobuf::internal::WireFormatLite::
      InternalWriteMessageNoVirtualToArray(
        1, this->rules(i), false, target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:google.api.Visibility)
  return target;
}

size_t Visibility::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:google.api.Visibility)
  size_t total_size = 0;

  // repeated .google.api.VisibilityRule rules = 1;
  {
    unsigned int count = this->rules_size();
    total_size += 1UL * count;
    for (unsigned int i = 0; i < count; i++) {
      total_size +=
        ::google::protobuf::internal::WireFormatLite::MessageSizeNoVirtual(
          this->rules(i));
    }
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void Visibility::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:google.api.Visibility)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const Visibility* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const Visibility>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:google.api.Visibility)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:google.api.Visibility)
    UnsafeMergeFrom(*source);
  }
}

void Visibility::MergeFrom(const Visibility& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:google.api.Visibility)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void Visibility::UnsafeMergeFrom(const Visibility& from) {
  GOOGLE_DCHECK(&from != this);
  rules_.MergeFrom(from.rules_);
}

void Visibility::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:google.api.Visibility)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void Visibility::CopyFrom(const Visibility& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:google.api.Visibility)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool Visibility::IsInitialized() const {

  return true;
}

void Visibility::Swap(Visibility* other) {
  if (other == this) return;
  if (GetArenaNoVirtual() == other->GetArenaNoVirtual()) {
    InternalSwap(other);
  } else {
    Visibility temp;
    temp.UnsafeMergeFrom(*this);
    CopyFrom(*other);
    other->CopyFrom(temp);
  }
}
void Visibility::UnsafeArenaSwap(Visibility* other) {
  if (other == this) return;
  GOOGLE_DCHECK(GetArenaNoVirtual() == other->GetArenaNoVirtual());
  InternalSwap(other);
}
void Visibility::InternalSwap(Visibility* other) {
  rules_.UnsafeArenaSwap(&other->rules_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata Visibility::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = Visibility_descriptor_;
  metadata.reflection = Visibility_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// Visibility

// repeated .google.api.VisibilityRule rules = 1;
int Visibility::rules_size() const {
  return rules_.size();
}
void Visibility::clear_rules() {
  rules_.Clear();
}
const ::google::api::VisibilityRule& Visibility::rules(int index) const {
  // @@protoc_insertion_point(field_get:google.api.Visibility.rules)
  return rules_.Get(index);
}
::google::api::VisibilityRule* Visibility::mutable_rules(int index) {
  // @@protoc_insertion_point(field_mutable:google.api.Visibility.rules)
  return rules_.Mutable(index);
}
::google::api::VisibilityRule* Visibility::add_rules() {
  // @@protoc_insertion_point(field_add:google.api.Visibility.rules)
  return rules_.Add();
}
::google::protobuf::RepeatedPtrField< ::google::api::VisibilityRule >*
Visibility::mutable_rules() {
  // @@protoc_insertion_point(field_mutable_list:google.api.Visibility.rules)
  return &rules_;
}
const ::google::protobuf::RepeatedPtrField< ::google::api::VisibilityRule >&
Visibility::rules() const {
  // @@protoc_insertion_point(field_list:google.api.Visibility.rules)
  return rules_;
}

inline const Visibility* Visibility::internal_default_instance() {
  return &Visibility_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int VisibilityRule::kSelectorFieldNumber;
const int VisibilityRule::kRestrictionFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

VisibilityRule::VisibilityRule()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:google.api.VisibilityRule)
}
VisibilityRule::VisibilityRule(::google::protobuf::Arena* arena)
  : ::google::protobuf::Message(),
  _internal_metadata_(arena) {
#ifdef GOOGLE_PROTOBUF_NO_STATIC_INITIALIZER
  protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
#endif  // GOOGLE_PROTOBUF_NO_STATIC_INITIALIZER
  SharedCtor();
  RegisterArenaDtor(arena);
  // @@protoc_insertion_point(arena_constructor:google.api.VisibilityRule)
}

void VisibilityRule::InitAsDefaultInstance() {
}

VisibilityRule::VisibilityRule(const VisibilityRule& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:google.api.VisibilityRule)
}

void VisibilityRule::SharedCtor() {
  selector_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  restriction_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  _cached_size_ = 0;
}

VisibilityRule::~VisibilityRule() {
  // @@protoc_insertion_point(destructor:google.api.VisibilityRule)
  SharedDtor();
}

void VisibilityRule::SharedDtor() {
  ::google::protobuf::Arena* arena = GetArenaNoVirtual();
  if (arena != NULL) {
    return;
  }

  selector_.Destroy(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), arena);
  restriction_.Destroy(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), arena);
}

void VisibilityRule::ArenaDtor(void* object) {
  VisibilityRule* _this = reinterpret_cast< VisibilityRule* >(object);
  (void)_this;
}
void VisibilityRule::RegisterArenaDtor(::google::protobuf::Arena* arena) {
}
void VisibilityRule::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* VisibilityRule::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return VisibilityRule_descriptor_;
}

const VisibilityRule& VisibilityRule::default_instance() {
  protobuf_InitDefaults_google_2fapi_2fvisibility_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<VisibilityRule> VisibilityRule_default_instance_;

VisibilityRule* VisibilityRule::New(::google::protobuf::Arena* arena) const {
  return ::google::protobuf::Arena::CreateMessage<VisibilityRule>(arena);
}

void VisibilityRule::Clear() {
// @@protoc_insertion_point(message_clear_start:google.api.VisibilityRule)
  selector_.ClearToEmpty(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
  restriction_.ClearToEmpty(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}

bool VisibilityRule::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:google.api.VisibilityRule)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional string selector = 1;
      case 1: {
        if (tag == 10) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_selector()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->selector().data(), this->selector().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "google.api.VisibilityRule.selector"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(18)) goto parse_restriction;
        break;
      }

      // optional string restriction = 2;
      case 2: {
        if (tag == 18) {
         parse_restriction:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_restriction()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->restriction().data(), this->restriction().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "google.api.VisibilityRule.restriction"));
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
  // @@protoc_insertion_point(parse_success:google.api.VisibilityRule)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:google.api.VisibilityRule)
  return false;
#undef DO_
}

void VisibilityRule::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:google.api.VisibilityRule)
  // optional string selector = 1;
  if (this->selector().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->selector().data(), this->selector().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.VisibilityRule.selector");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      1, this->selector(), output);
  }

  // optional string restriction = 2;
  if (this->restriction().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->restriction().data(), this->restriction().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.VisibilityRule.restriction");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      2, this->restriction(), output);
  }

  // @@protoc_insertion_point(serialize_end:google.api.VisibilityRule)
}

::google::protobuf::uint8* VisibilityRule::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:google.api.VisibilityRule)
  // optional string selector = 1;
  if (this->selector().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->selector().data(), this->selector().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.VisibilityRule.selector");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->selector(), target);
  }

  // optional string restriction = 2;
  if (this->restriction().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->restriction().data(), this->restriction().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "google.api.VisibilityRule.restriction");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->restriction(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:google.api.VisibilityRule)
  return target;
}

size_t VisibilityRule::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:google.api.VisibilityRule)
  size_t total_size = 0;

  // optional string selector = 1;
  if (this->selector().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->selector());
  }

  // optional string restriction = 2;
  if (this->restriction().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->restriction());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void VisibilityRule::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:google.api.VisibilityRule)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const VisibilityRule* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const VisibilityRule>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:google.api.VisibilityRule)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:google.api.VisibilityRule)
    UnsafeMergeFrom(*source);
  }
}

void VisibilityRule::MergeFrom(const VisibilityRule& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:google.api.VisibilityRule)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void VisibilityRule::UnsafeMergeFrom(const VisibilityRule& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.selector().size() > 0) {
    set_selector(from.selector());
  }
  if (from.restriction().size() > 0) {
    set_restriction(from.restriction());
  }
}

void VisibilityRule::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:google.api.VisibilityRule)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void VisibilityRule::CopyFrom(const VisibilityRule& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:google.api.VisibilityRule)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool VisibilityRule::IsInitialized() const {

  return true;
}

void VisibilityRule::Swap(VisibilityRule* other) {
  if (other == this) return;
  if (GetArenaNoVirtual() == other->GetArenaNoVirtual()) {
    InternalSwap(other);
  } else {
    VisibilityRule temp;
    temp.UnsafeMergeFrom(*this);
    CopyFrom(*other);
    other->CopyFrom(temp);
  }
}
void VisibilityRule::UnsafeArenaSwap(VisibilityRule* other) {
  if (other == this) return;
  GOOGLE_DCHECK(GetArenaNoVirtual() == other->GetArenaNoVirtual());
  InternalSwap(other);
}
void VisibilityRule::InternalSwap(VisibilityRule* other) {
  selector_.Swap(&other->selector_);
  restriction_.Swap(&other->restriction_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata VisibilityRule::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = VisibilityRule_descriptor_;
  metadata.reflection = VisibilityRule_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// VisibilityRule

// optional string selector = 1;
void VisibilityRule::clear_selector() {
  selector_.ClearToEmpty(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}
const ::std::string& VisibilityRule::selector() const {
  // @@protoc_insertion_point(field_get:google.api.VisibilityRule.selector)
  return selector_.Get(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void VisibilityRule::set_selector(const ::std::string& value) {
  
  selector_.Set(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value, GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set:google.api.VisibilityRule.selector)
}
void VisibilityRule::set_selector(const char* value) {
  
  selector_.Set(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value),
              GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set_char:google.api.VisibilityRule.selector)
}
void VisibilityRule::set_selector(const char* value,
    size_t size) {
  
  selector_.Set(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(
      reinterpret_cast<const char*>(value), size), GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set_pointer:google.api.VisibilityRule.selector)
}
::std::string* VisibilityRule::mutable_selector() {
  
  // @@protoc_insertion_point(field_mutable:google.api.VisibilityRule.selector)
  return selector_.Mutable(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}
::std::string* VisibilityRule::release_selector() {
  // @@protoc_insertion_point(field_release:google.api.VisibilityRule.selector)
  
  return selector_.Release(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}
::std::string* VisibilityRule::unsafe_arena_release_selector() {
  // @@protoc_insertion_point(field_unsafe_arena_release:google.api.VisibilityRule.selector)
  GOOGLE_DCHECK(GetArenaNoVirtual() != NULL);
  
  return selector_.UnsafeArenaRelease(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      GetArenaNoVirtual());
}
void VisibilityRule::set_allocated_selector(::std::string* selector) {
  if (selector != NULL) {
    
  } else {
    
  }
  selector_.SetAllocated(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), selector,
      GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set_allocated:google.api.VisibilityRule.selector)
}
void VisibilityRule::unsafe_arena_set_allocated_selector(
    ::std::string* selector) {
  GOOGLE_DCHECK(GetArenaNoVirtual() != NULL);
  if (selector != NULL) {
    
  } else {
    
  }
  selector_.UnsafeArenaSetAllocated(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      selector, GetArenaNoVirtual());
  // @@protoc_insertion_point(field_unsafe_arena_set_allocated:google.api.VisibilityRule.selector)
}

// optional string restriction = 2;
void VisibilityRule::clear_restriction() {
  restriction_.ClearToEmpty(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}
const ::std::string& VisibilityRule::restriction() const {
  // @@protoc_insertion_point(field_get:google.api.VisibilityRule.restriction)
  return restriction_.Get(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void VisibilityRule::set_restriction(const ::std::string& value) {
  
  restriction_.Set(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value, GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set:google.api.VisibilityRule.restriction)
}
void VisibilityRule::set_restriction(const char* value) {
  
  restriction_.Set(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value),
              GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set_char:google.api.VisibilityRule.restriction)
}
void VisibilityRule::set_restriction(const char* value,
    size_t size) {
  
  restriction_.Set(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(
      reinterpret_cast<const char*>(value), size), GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set_pointer:google.api.VisibilityRule.restriction)
}
::std::string* VisibilityRule::mutable_restriction() {
  
  // @@protoc_insertion_point(field_mutable:google.api.VisibilityRule.restriction)
  return restriction_.Mutable(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}
::std::string* VisibilityRule::release_restriction() {
  // @@protoc_insertion_point(field_release:google.api.VisibilityRule.restriction)
  
  return restriction_.Release(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), GetArenaNoVirtual());
}
::std::string* VisibilityRule::unsafe_arena_release_restriction() {
  // @@protoc_insertion_point(field_unsafe_arena_release:google.api.VisibilityRule.restriction)
  GOOGLE_DCHECK(GetArenaNoVirtual() != NULL);
  
  return restriction_.UnsafeArenaRelease(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      GetArenaNoVirtual());
}
void VisibilityRule::set_allocated_restriction(::std::string* restriction) {
  if (restriction != NULL) {
    
  } else {
    
  }
  restriction_.SetAllocated(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), restriction,
      GetArenaNoVirtual());
  // @@protoc_insertion_point(field_set_allocated:google.api.VisibilityRule.restriction)
}
void VisibilityRule::unsafe_arena_set_allocated_restriction(
    ::std::string* restriction) {
  GOOGLE_DCHECK(GetArenaNoVirtual() != NULL);
  if (restriction != NULL) {
    
  } else {
    
  }
  restriction_.UnsafeArenaSetAllocated(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      restriction, GetArenaNoVirtual());
  // @@protoc_insertion_point(field_unsafe_arena_set_allocated:google.api.VisibilityRule.restriction)
}

inline const VisibilityRule* VisibilityRule::internal_default_instance() {
  return &VisibilityRule_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::EnumOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::VisibilityRule >, 11, false >
  enum_visibility(kEnumVisibilityFieldNumber, *::google::api::VisibilityRule::internal_default_instance());
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::EnumValueOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::VisibilityRule >, 11, false >
  value_visibility(kValueVisibilityFieldNumber, *::google::api::VisibilityRule::internal_default_instance());
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::FieldOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::VisibilityRule >, 11, false >
  field_visibility(kFieldVisibilityFieldNumber, *::google::api::VisibilityRule::internal_default_instance());
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::MessageOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::VisibilityRule >, 11, false >
  message_visibility(kMessageVisibilityFieldNumber, *::google::api::VisibilityRule::internal_default_instance());
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::MethodOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::VisibilityRule >, 11, false >
  method_visibility(kMethodVisibilityFieldNumber, *::google::api::VisibilityRule::internal_default_instance());
::google::protobuf::internal::ExtensionIdentifier< ::google::protobuf::ServiceOptions,
    ::google::protobuf::internal::MessageTypeTraits< ::google::api::VisibilityRule >, 11, false >
  api_visibility(kApiVisibilityFieldNumber, *::google::api::VisibilityRule::internal_default_instance());

// @@protoc_insertion_point(namespace_scope)

}  // namespace api
}  // namespace google

// @@protoc_insertion_point(global_scope)
