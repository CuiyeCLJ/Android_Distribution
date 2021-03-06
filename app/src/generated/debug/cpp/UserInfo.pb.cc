// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserInfo.proto

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "UserInfo.pb.h"

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

namespace bean {

namespace {

const ::google::protobuf::Descriptor* UserInfo_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  UserInfo_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_UserInfo_2eproto() GOOGLE_ATTRIBUTE_COLD;
void protobuf_AssignDesc_UserInfo_2eproto() {
  protobuf_AddDesc_UserInfo_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "UserInfo.proto");
  GOOGLE_CHECK(file != NULL);
  UserInfo_descriptor_ = file->message_type(0);
  static const int UserInfo_offsets_[6] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, status_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, user_id_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, nick_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, sex_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, portrait_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, signature_),
  };
  UserInfo_reflection_ =
    ::google::protobuf::internal::GeneratedMessageReflection::NewGeneratedMessageReflection(
      UserInfo_descriptor_,
      UserInfo::internal_default_instance(),
      UserInfo_offsets_,
      -1,
      -1,
      -1,
      sizeof(UserInfo),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(UserInfo, _internal_metadata_));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_UserInfo_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) GOOGLE_ATTRIBUTE_COLD;
void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
      UserInfo_descriptor_, UserInfo::internal_default_instance());
}

}  // namespace

void protobuf_ShutdownFile_UserInfo_2eproto() {
  UserInfo_default_instance_.Shutdown();
  delete UserInfo_reflection_;
}

void protobuf_InitDefaults_UserInfo_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::internal::GetEmptyString();
  UserInfo_default_instance_.DefaultConstruct();
  UserInfo_default_instance_.get_mutable()->InitAsDefaultInstance();
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_InitDefaults_UserInfo_2eproto_once_);
void protobuf_InitDefaults_UserInfo_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_InitDefaults_UserInfo_2eproto_once_,
                 &protobuf_InitDefaults_UserInfo_2eproto_impl);
}
void protobuf_AddDesc_UserInfo_2eproto_impl() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  protobuf_InitDefaults_UserInfo_2eproto();
  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\016UserInfo.proto\022\004bean\"k\n\010UserInfo\022\016\n\006st"
    "atus\030\001 \001(\005\022\017\n\007user_id\030\002 \001(\003\022\014\n\004nick\030\003 \001("
    "\t\022\013\n\003sex\030\004 \001(\005\022\020\n\010portrait\030\005 \001(\t\022\021\n\tsign"
    "ature\030\006 \001(\tB*\n(com.liongjfuan.android_di"
    "stribution.beanb\006proto3", 183);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "UserInfo.proto", &protobuf_RegisterTypes);
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_UserInfo_2eproto);
}

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AddDesc_UserInfo_2eproto_once_);
void protobuf_AddDesc_UserInfo_2eproto() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AddDesc_UserInfo_2eproto_once_,
                 &protobuf_AddDesc_UserInfo_2eproto_impl);
}
// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_UserInfo_2eproto {
  StaticDescriptorInitializer_UserInfo_2eproto() {
    protobuf_AddDesc_UserInfo_2eproto();
  }
} static_descriptor_initializer_UserInfo_2eproto_;

namespace {

static void MergeFromFail(int line) GOOGLE_ATTRIBUTE_COLD GOOGLE_ATTRIBUTE_NORETURN;
static void MergeFromFail(int line) {
  ::google::protobuf::internal::MergeFromFail(__FILE__, line);
}

}  // namespace


// ===================================================================

#if !defined(_MSC_VER) || _MSC_VER >= 1900
const int UserInfo::kStatusFieldNumber;
const int UserInfo::kUserIdFieldNumber;
const int UserInfo::kNickFieldNumber;
const int UserInfo::kSexFieldNumber;
const int UserInfo::kPortraitFieldNumber;
const int UserInfo::kSignatureFieldNumber;
#endif  // !defined(_MSC_VER) || _MSC_VER >= 1900

UserInfo::UserInfo()
  : ::google::protobuf::Message(), _internal_metadata_(NULL) {
  if (this != internal_default_instance()) protobuf_InitDefaults_UserInfo_2eproto();
  SharedCtor();
  // @@protoc_insertion_point(constructor:bean.UserInfo)
}

void UserInfo::InitAsDefaultInstance() {
}

UserInfo::UserInfo(const UserInfo& from)
  : ::google::protobuf::Message(),
    _internal_metadata_(NULL) {
  SharedCtor();
  UnsafeMergeFrom(from);
  // @@protoc_insertion_point(copy_constructor:bean.UserInfo)
}

void UserInfo::SharedCtor() {
  nick_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  portrait_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  signature_.UnsafeSetDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  ::memset(&user_id_, 0, reinterpret_cast<char*>(&sex_) -
    reinterpret_cast<char*>(&user_id_) + sizeof(sex_));
  _cached_size_ = 0;
}

UserInfo::~UserInfo() {
  // @@protoc_insertion_point(destructor:bean.UserInfo)
  SharedDtor();
}

void UserInfo::SharedDtor() {
  nick_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  portrait_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  signature_.DestroyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}

void UserInfo::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* UserInfo::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return UserInfo_descriptor_;
}

const UserInfo& UserInfo::default_instance() {
  protobuf_InitDefaults_UserInfo_2eproto();
  return *internal_default_instance();
}

::google::protobuf::internal::ExplicitlyConstructed<UserInfo> UserInfo_default_instance_;

UserInfo* UserInfo::New(::google::protobuf::Arena* arena) const {
  UserInfo* n = new UserInfo;
  if (arena != NULL) {
    arena->Own(n);
  }
  return n;
}

void UserInfo::Clear() {
// @@protoc_insertion_point(message_clear_start:bean.UserInfo)
#if defined(__clang__)
#define ZR_HELPER_(f) \
  _Pragma("clang diagnostic push") \
  _Pragma("clang diagnostic ignored \"-Winvalid-offsetof\"") \
  __builtin_offsetof(UserInfo, f) \
  _Pragma("clang diagnostic pop")
#else
#define ZR_HELPER_(f) reinterpret_cast<char*>(\
  &reinterpret_cast<UserInfo*>(16)->f)
#endif

#define ZR_(first, last) do {\
  ::memset(&(first), 0,\
           ZR_HELPER_(last) - ZR_HELPER_(first) + sizeof(last));\
} while (0)

  ZR_(user_id_, sex_);
  nick_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  portrait_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  signature_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());

#undef ZR_HELPER_
#undef ZR_

}

bool UserInfo::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!GOOGLE_PREDICT_TRUE(EXPRESSION)) goto failure
  ::google::protobuf::uint32 tag;
  // @@protoc_insertion_point(parse_start:bean.UserInfo)
  for (;;) {
    ::std::pair< ::google::protobuf::uint32, bool> p = input->ReadTagWithCutoff(127);
    tag = p.first;
    if (!p.second) goto handle_unusual;
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // optional int32 status = 1;
      case 1: {
        if (tag == 8) {

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &status_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(16)) goto parse_user_id;
        break;
      }

      // optional int64 user_id = 2;
      case 2: {
        if (tag == 16) {
         parse_user_id:

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int64, ::google::protobuf::internal::WireFormatLite::TYPE_INT64>(
                 input, &user_id_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(26)) goto parse_nick;
        break;
      }

      // optional string nick = 3;
      case 3: {
        if (tag == 26) {
         parse_nick:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_nick()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->nick().data(), this->nick().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "bean.UserInfo.nick"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(32)) goto parse_sex;
        break;
      }

      // optional int32 sex = 4;
      case 4: {
        if (tag == 32) {
         parse_sex:

          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::int32, ::google::protobuf::internal::WireFormatLite::TYPE_INT32>(
                 input, &sex_)));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(42)) goto parse_portrait;
        break;
      }

      // optional string portrait = 5;
      case 5: {
        if (tag == 42) {
         parse_portrait:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_portrait()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->portrait().data(), this->portrait().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "bean.UserInfo.portrait"));
        } else {
          goto handle_unusual;
        }
        if (input->ExpectTag(50)) goto parse_signature;
        break;
      }

      // optional string signature = 6;
      case 6: {
        if (tag == 50) {
         parse_signature:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_signature()));
          DO_(::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            this->signature().data(), this->signature().length(),
            ::google::protobuf::internal::WireFormatLite::PARSE,
            "bean.UserInfo.signature"));
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
  // @@protoc_insertion_point(parse_success:bean.UserInfo)
  return true;
failure:
  // @@protoc_insertion_point(parse_failure:bean.UserInfo)
  return false;
#undef DO_
}

void UserInfo::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // @@protoc_insertion_point(serialize_start:bean.UserInfo)
  // optional int32 status = 1;
  if (this->status() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(1, this->status(), output);
  }

  // optional int64 user_id = 2;
  if (this->user_id() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt64(2, this->user_id(), output);
  }

  // optional string nick = 3;
  if (this->nick().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->nick().data(), this->nick().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "bean.UserInfo.nick");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      3, this->nick(), output);
  }

  // optional int32 sex = 4;
  if (this->sex() != 0) {
    ::google::protobuf::internal::WireFormatLite::WriteInt32(4, this->sex(), output);
  }

  // optional string portrait = 5;
  if (this->portrait().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->portrait().data(), this->portrait().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "bean.UserInfo.portrait");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      5, this->portrait(), output);
  }

  // optional string signature = 6;
  if (this->signature().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->signature().data(), this->signature().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "bean.UserInfo.signature");
    ::google::protobuf::internal::WireFormatLite::WriteStringMaybeAliased(
      6, this->signature(), output);
  }

  // @@protoc_insertion_point(serialize_end:bean.UserInfo)
}

::google::protobuf::uint8* UserInfo::InternalSerializeWithCachedSizesToArray(
    bool deterministic, ::google::protobuf::uint8* target) const {
  (void)deterministic; // Unused
  // @@protoc_insertion_point(serialize_to_array_start:bean.UserInfo)
  // optional int32 status = 1;
  if (this->status() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(1, this->status(), target);
  }

  // optional int64 user_id = 2;
  if (this->user_id() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArray(2, this->user_id(), target);
  }

  // optional string nick = 3;
  if (this->nick().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->nick().data(), this->nick().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "bean.UserInfo.nick");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        3, this->nick(), target);
  }

  // optional int32 sex = 4;
  if (this->sex() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArray(4, this->sex(), target);
  }

  // optional string portrait = 5;
  if (this->portrait().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->portrait().data(), this->portrait().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "bean.UserInfo.portrait");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        5, this->portrait(), target);
  }

  // optional string signature = 6;
  if (this->signature().size() > 0) {
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
      this->signature().data(), this->signature().length(),
      ::google::protobuf::internal::WireFormatLite::SERIALIZE,
      "bean.UserInfo.signature");
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        6, this->signature(), target);
  }

  // @@protoc_insertion_point(serialize_to_array_end:bean.UserInfo)
  return target;
}

size_t UserInfo::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:bean.UserInfo)
  size_t total_size = 0;

  // optional int32 status = 1;
  if (this->status() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->status());
  }

  // optional int64 user_id = 2;
  if (this->user_id() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int64Size(
        this->user_id());
  }

  // optional string nick = 3;
  if (this->nick().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->nick());
  }

  // optional int32 sex = 4;
  if (this->sex() != 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::Int32Size(
        this->sex());
  }

  // optional string portrait = 5;
  if (this->portrait().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->portrait());
  }

  // optional string signature = 6;
  if (this->signature().size() > 0) {
    total_size += 1 +
      ::google::protobuf::internal::WireFormatLite::StringSize(
        this->signature());
  }

  int cached_size = ::google::protobuf::internal::ToCachedSize(total_size);
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = cached_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void UserInfo::MergeFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:bean.UserInfo)
  if (GOOGLE_PREDICT_FALSE(&from == this)) MergeFromFail(__LINE__);
  const UserInfo* source =
      ::google::protobuf::internal::DynamicCastToGenerated<const UserInfo>(
          &from);
  if (source == NULL) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:bean.UserInfo)
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:bean.UserInfo)
    UnsafeMergeFrom(*source);
  }
}

void UserInfo::MergeFrom(const UserInfo& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:bean.UserInfo)
  if (GOOGLE_PREDICT_TRUE(&from != this)) {
    UnsafeMergeFrom(from);
  } else {
    MergeFromFail(__LINE__);
  }
}

void UserInfo::UnsafeMergeFrom(const UserInfo& from) {
  GOOGLE_DCHECK(&from != this);
  if (from.status() != 0) {
    set_status(from.status());
  }
  if (from.user_id() != 0) {
    set_user_id(from.user_id());
  }
  if (from.nick().size() > 0) {

    nick_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.nick_);
  }
  if (from.sex() != 0) {
    set_sex(from.sex());
  }
  if (from.portrait().size() > 0) {

    portrait_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.portrait_);
  }
  if (from.signature().size() > 0) {

    signature_.AssignWithDefault(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), from.signature_);
  }
}

void UserInfo::CopyFrom(const ::google::protobuf::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:bean.UserInfo)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void UserInfo::CopyFrom(const UserInfo& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:bean.UserInfo)
  if (&from == this) return;
  Clear();
  UnsafeMergeFrom(from);
}

bool UserInfo::IsInitialized() const {

  return true;
}

void UserInfo::Swap(UserInfo* other) {
  if (other == this) return;
  InternalSwap(other);
}
void UserInfo::InternalSwap(UserInfo* other) {
  std::swap(status_, other->status_);
  std::swap(user_id_, other->user_id_);
  nick_.Swap(&other->nick_);
  std::swap(sex_, other->sex_);
  portrait_.Swap(&other->portrait_);
  signature_.Swap(&other->signature_);
  _internal_metadata_.Swap(&other->_internal_metadata_);
  std::swap(_cached_size_, other->_cached_size_);
}

::google::protobuf::Metadata UserInfo::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = UserInfo_descriptor_;
  metadata.reflection = UserInfo_reflection_;
  return metadata;
}

#if PROTOBUF_INLINE_NOT_IN_HEADERS
// UserInfo

// optional int32 status = 1;
void UserInfo::clear_status() {
  status_ = 0;
}
::google::protobuf::int32 UserInfo::status() const {
  // @@protoc_insertion_point(field_get:bean.UserInfo.status)
  return status_;
}
void UserInfo::set_status(::google::protobuf::int32 value) {
  
  status_ = value;
  // @@protoc_insertion_point(field_set:bean.UserInfo.status)
}

// optional int64 user_id = 2;
void UserInfo::clear_user_id() {
  user_id_ = GOOGLE_LONGLONG(0);
}
::google::protobuf::int64 UserInfo::user_id() const {
  // @@protoc_insertion_point(field_get:bean.UserInfo.user_id)
  return user_id_;
}
void UserInfo::set_user_id(::google::protobuf::int64 value) {
  
  user_id_ = value;
  // @@protoc_insertion_point(field_set:bean.UserInfo.user_id)
}

// optional string nick = 3;
void UserInfo::clear_nick() {
  nick_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& UserInfo::nick() const {
  // @@protoc_insertion_point(field_get:bean.UserInfo.nick)
  return nick_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void UserInfo::set_nick(const ::std::string& value) {
  
  nick_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:bean.UserInfo.nick)
}
void UserInfo::set_nick(const char* value) {
  
  nick_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:bean.UserInfo.nick)
}
void UserInfo::set_nick(const char* value, size_t size) {
  
  nick_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:bean.UserInfo.nick)
}
::std::string* UserInfo::mutable_nick() {
  
  // @@protoc_insertion_point(field_mutable:bean.UserInfo.nick)
  return nick_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* UserInfo::release_nick() {
  // @@protoc_insertion_point(field_release:bean.UserInfo.nick)
  
  return nick_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void UserInfo::set_allocated_nick(::std::string* nick) {
  if (nick != NULL) {
    
  } else {
    
  }
  nick_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), nick);
  // @@protoc_insertion_point(field_set_allocated:bean.UserInfo.nick)
}

// optional int32 sex = 4;
void UserInfo::clear_sex() {
  sex_ = 0;
}
::google::protobuf::int32 UserInfo::sex() const {
  // @@protoc_insertion_point(field_get:bean.UserInfo.sex)
  return sex_;
}
void UserInfo::set_sex(::google::protobuf::int32 value) {
  
  sex_ = value;
  // @@protoc_insertion_point(field_set:bean.UserInfo.sex)
}

// optional string portrait = 5;
void UserInfo::clear_portrait() {
  portrait_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& UserInfo::portrait() const {
  // @@protoc_insertion_point(field_get:bean.UserInfo.portrait)
  return portrait_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void UserInfo::set_portrait(const ::std::string& value) {
  
  portrait_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:bean.UserInfo.portrait)
}
void UserInfo::set_portrait(const char* value) {
  
  portrait_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:bean.UserInfo.portrait)
}
void UserInfo::set_portrait(const char* value, size_t size) {
  
  portrait_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:bean.UserInfo.portrait)
}
::std::string* UserInfo::mutable_portrait() {
  
  // @@protoc_insertion_point(field_mutable:bean.UserInfo.portrait)
  return portrait_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* UserInfo::release_portrait() {
  // @@protoc_insertion_point(field_release:bean.UserInfo.portrait)
  
  return portrait_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void UserInfo::set_allocated_portrait(::std::string* portrait) {
  if (portrait != NULL) {
    
  } else {
    
  }
  portrait_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), portrait);
  // @@protoc_insertion_point(field_set_allocated:bean.UserInfo.portrait)
}

// optional string signature = 6;
void UserInfo::clear_signature() {
  signature_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
const ::std::string& UserInfo::signature() const {
  // @@protoc_insertion_point(field_get:bean.UserInfo.signature)
  return signature_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void UserInfo::set_signature(const ::std::string& value) {
  
  signature_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:bean.UserInfo.signature)
}
void UserInfo::set_signature(const char* value) {
  
  signature_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:bean.UserInfo.signature)
}
void UserInfo::set_signature(const char* value, size_t size) {
  
  signature_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:bean.UserInfo.signature)
}
::std::string* UserInfo::mutable_signature() {
  
  // @@protoc_insertion_point(field_mutable:bean.UserInfo.signature)
  return signature_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
::std::string* UserInfo::release_signature() {
  // @@protoc_insertion_point(field_release:bean.UserInfo.signature)
  
  return signature_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
void UserInfo::set_allocated_signature(::std::string* signature) {
  if (signature != NULL) {
    
  } else {
    
  }
  signature_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), signature);
  // @@protoc_insertion_point(field_set_allocated:bean.UserInfo.signature)
}

inline const UserInfo* UserInfo::internal_default_instance() {
  return &UserInfo_default_instance_.get();
}
#endif  // PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace bean

// @@protoc_insertion_point(global_scope)
