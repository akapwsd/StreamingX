// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: channelBase.proto

#ifndef PROTOBUF_channelBase_2eproto__INCLUDED
#define PROTOBUF_channelBase_2eproto__INCLUDED

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
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/unknown_field_set.h>
// @@protoc_insertion_point(includes)

namespace uyujoy {
namespace com {
namespace api {
namespace channel {
namespace frontend {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_channelBase_2eproto();
void protobuf_InitDefaults_channelBase_2eproto();
void protobuf_AssignDesc_channelBase_2eproto();
void protobuf_ShutdownFile_channelBase_2eproto();

class channel;
class channelUser;

// ===================================================================

class channel : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.com.api.channel.frontend.channel) */ {
 public:
  channel();
  virtual ~channel();

  channel(const channel& from);

  inline channel& operator=(const channel& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const channel& default_instance();

  static const channel* internal_default_instance();

  void Swap(channel* other);

  // implements Message ----------------------------------------------

  inline channel* New() const { return New(NULL); }

  channel* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const channel& from);
  void MergeFrom(const channel& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(channel* other);
  void UnsafeMergeFrom(const channel& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional string id = 1;
  void clear_id();
  static const int kIdFieldNumber = 1;
  const ::std::string& id() const;
  void set_id(const ::std::string& value);
  void set_id(const char* value);
  void set_id(const char* value, size_t size);
  ::std::string* mutable_id();
  ::std::string* release_id();
  void set_allocated_id(::std::string* id);

  // optional uint32 category = 2;
  void clear_category();
  static const int kCategoryFieldNumber = 2;
  ::google::protobuf::uint32 category() const;
  void set_category(::google::protobuf::uint32 value);

  // optional uint32 state = 3;
  void clear_state();
  static const int kStateFieldNumber = 3;
  ::google::protobuf::uint32 state() const;
  void set_state(::google::protobuf::uint32 value);

  // @@protoc_insertion_point(class_scope:uyujoy.com.api.channel.frontend.channel)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr id_;
  ::google::protobuf::uint32 category_;
  ::google::protobuf::uint32 state_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_channelBase_2eproto_impl();
  friend void  protobuf_AddDesc_channelBase_2eproto_impl();
  friend void protobuf_AssignDesc_channelBase_2eproto();
  friend void protobuf_ShutdownFile_channelBase_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<channel> channel_default_instance_;

// -------------------------------------------------------------------

class channelUser : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.com.api.channel.frontend.channelUser) */ {
 public:
  channelUser();
  virtual ~channelUser();

  channelUser(const channelUser& from);

  inline channelUser& operator=(const channelUser& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const channelUser& default_instance();

  static const channelUser* internal_default_instance();

  void Swap(channelUser* other);

  // implements Message ----------------------------------------------

  inline channelUser* New() const { return New(NULL); }

  channelUser* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const channelUser& from);
  void MergeFrom(const channelUser& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(channelUser* other);
  void UnsafeMergeFrom(const channelUser& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional string channelId = 1;
  void clear_channelid();
  static const int kChannelIdFieldNumber = 1;
  const ::std::string& channelid() const;
  void set_channelid(const ::std::string& value);
  void set_channelid(const char* value);
  void set_channelid(const char* value, size_t size);
  ::std::string* mutable_channelid();
  ::std::string* release_channelid();
  void set_allocated_channelid(::std::string* channelid);

  // optional string uid = 2;
  void clear_uid();
  static const int kUidFieldNumber = 2;
  const ::std::string& uid() const;
  void set_uid(const ::std::string& value);
  void set_uid(const char* value);
  void set_uid(const char* value, size_t size);
  ::std::string* mutable_uid();
  ::std::string* release_uid();
  void set_allocated_uid(::std::string* uid);

  // optional uint32 state = 3;
  void clear_state();
  static const int kStateFieldNumber = 3;
  ::google::protobuf::uint32 state() const;
  void set_state(::google::protobuf::uint32 value);

  // optional uint32 role = 4;
  void clear_role();
  static const int kRoleFieldNumber = 4;
  ::google::protobuf::uint32 role() const;
  void set_role(::google::protobuf::uint32 value);

  // @@protoc_insertion_point(class_scope:uyujoy.com.api.channel.frontend.channelUser)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr channelid_;
  ::google::protobuf::internal::ArenaStringPtr uid_;
  ::google::protobuf::uint32 state_;
  ::google::protobuf::uint32 role_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_channelBase_2eproto_impl();
  friend void  protobuf_AddDesc_channelBase_2eproto_impl();
  friend void protobuf_AssignDesc_channelBase_2eproto();
  friend void protobuf_ShutdownFile_channelBase_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<channelUser> channelUser_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// channel

// optional string id = 1;
inline void channel::clear_id() {
  id_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& channel::id() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channel.id)
  return id_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void channel::set_id(const ::std::string& value) {
  
  id_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channel.id)
}
inline void channel::set_id(const char* value) {
  
  id_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:uyujoy.com.api.channel.frontend.channel.id)
}
inline void channel::set_id(const char* value, size_t size) {
  
  id_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:uyujoy.com.api.channel.frontend.channel.id)
}
inline ::std::string* channel::mutable_id() {
  
  // @@protoc_insertion_point(field_mutable:uyujoy.com.api.channel.frontend.channel.id)
  return id_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* channel::release_id() {
  // @@protoc_insertion_point(field_release:uyujoy.com.api.channel.frontend.channel.id)
  
  return id_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void channel::set_allocated_id(::std::string* id) {
  if (id != NULL) {
    
  } else {
    
  }
  id_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), id);
  // @@protoc_insertion_point(field_set_allocated:uyujoy.com.api.channel.frontend.channel.id)
}

// optional uint32 category = 2;
inline void channel::clear_category() {
  category_ = 0u;
}
inline ::google::protobuf::uint32 channel::category() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channel.category)
  return category_;
}
inline void channel::set_category(::google::protobuf::uint32 value) {
  
  category_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channel.category)
}

// optional uint32 state = 3;
inline void channel::clear_state() {
  state_ = 0u;
}
inline ::google::protobuf::uint32 channel::state() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channel.state)
  return state_;
}
inline void channel::set_state(::google::protobuf::uint32 value) {
  
  state_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channel.state)
}

inline const channel* channel::internal_default_instance() {
  return &channel_default_instance_.get();
}
// -------------------------------------------------------------------

// channelUser

// optional string channelId = 1;
inline void channelUser::clear_channelid() {
  channelid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& channelUser::channelid() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelUser.channelId)
  return channelid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void channelUser::set_channelid(const ::std::string& value) {
  
  channelid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channelUser.channelId)
}
inline void channelUser::set_channelid(const char* value) {
  
  channelid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:uyujoy.com.api.channel.frontend.channelUser.channelId)
}
inline void channelUser::set_channelid(const char* value, size_t size) {
  
  channelid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:uyujoy.com.api.channel.frontend.channelUser.channelId)
}
inline ::std::string* channelUser::mutable_channelid() {
  
  // @@protoc_insertion_point(field_mutable:uyujoy.com.api.channel.frontend.channelUser.channelId)
  return channelid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* channelUser::release_channelid() {
  // @@protoc_insertion_point(field_release:uyujoy.com.api.channel.frontend.channelUser.channelId)
  
  return channelid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void channelUser::set_allocated_channelid(::std::string* channelid) {
  if (channelid != NULL) {
    
  } else {
    
  }
  channelid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), channelid);
  // @@protoc_insertion_point(field_set_allocated:uyujoy.com.api.channel.frontend.channelUser.channelId)
}

// optional string uid = 2;
inline void channelUser::clear_uid() {
  uid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& channelUser::uid() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelUser.uid)
  return uid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void channelUser::set_uid(const ::std::string& value) {
  
  uid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channelUser.uid)
}
inline void channelUser::set_uid(const char* value) {
  
  uid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:uyujoy.com.api.channel.frontend.channelUser.uid)
}
inline void channelUser::set_uid(const char* value, size_t size) {
  
  uid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:uyujoy.com.api.channel.frontend.channelUser.uid)
}
inline ::std::string* channelUser::mutable_uid() {
  
  // @@protoc_insertion_point(field_mutable:uyujoy.com.api.channel.frontend.channelUser.uid)
  return uid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* channelUser::release_uid() {
  // @@protoc_insertion_point(field_release:uyujoy.com.api.channel.frontend.channelUser.uid)
  
  return uid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void channelUser::set_allocated_uid(::std::string* uid) {
  if (uid != NULL) {
    
  } else {
    
  }
  uid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), uid);
  // @@protoc_insertion_point(field_set_allocated:uyujoy.com.api.channel.frontend.channelUser.uid)
}

// optional uint32 state = 3;
inline void channelUser::clear_state() {
  state_ = 0u;
}
inline ::google::protobuf::uint32 channelUser::state() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelUser.state)
  return state_;
}
inline void channelUser::set_state(::google::protobuf::uint32 value) {
  
  state_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channelUser.state)
}

// optional uint32 role = 4;
inline void channelUser::clear_role() {
  role_ = 0u;
}
inline ::google::protobuf::uint32 channelUser::role() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.channel.frontend.channelUser.role)
  return role_;
}
inline void channelUser::set_role(::google::protobuf::uint32 value) {
  
  role_ = value;
  // @@protoc_insertion_point(field_set:uyujoy.com.api.channel.frontend.channelUser.role)
}

inline const channelUser* channelUser::internal_default_instance() {
  return &channelUser_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace frontend
}  // namespace channel
}  // namespace api
}  // namespace com
}  // namespace uyujoy

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_channelBase_2eproto__INCLUDED